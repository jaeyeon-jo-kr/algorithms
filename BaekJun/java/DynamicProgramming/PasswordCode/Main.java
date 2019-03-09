import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //Scanner sc = new Scanner(System.in);
        //String passwordStr = sc.nextLine();   
        //sc.close();        
        Scanner problemScanner = new Scanner(new File("A.dat.txt"));
        Scanner solveScanner = new Scanner(new File("A.diff.txt"));

        while(problemScanner.hasNext() && solveScanner.hasNext()){
            String problem = problemScanner.nextLine();
            String solution = solveScanner.nextLine();
            System.out.println("--------");
            System.out.println(problem);
            System.out.println(new Main().solve(problem));
            System.out.println(solution);
        }
        problemScanner.close();
        solveScanner.close();
    }
    
    public String solve(String input)
    {
        int pwd[] = new int[input.length()];
        int length = 0;

        for(char var : input.toCharArray())
        {            
            pwd[length++] = (int)var - (int)'0';            
        }

        int dp[] = new int[length];

        if(pwd[0] == 0)
            dp[0] = 0;
        else dp[0] = 1; //not used previous value        
                
        for(int i = 1; i < length; i++ )
        {
            if(pwd[i-1] == 0){ //00~09
                if(pwd[i] > 0) //01~09
                {                  
                    if(i > 1)
                        dp[i] = dp[i-1];
                    else
                        dp[i] = 0;
                }
                else{//00
                    dp[i] = 0;
                }
            }else if(pwd[i-1] == 1)// 10 ~19
            {
                if(pwd[i] > 0) //11~19
                {
                    if(i > 1)
                        dp[i] = dp[i-1] + dp[i-2];
                    else
                        dp[i] = 2;
                }else{ //10
                    if(i>1)
                        dp[i] = dp[i-2];
                    else
                        dp[i] = 1;
                }
            }else if(pwd[i-1] == 2)// 20~29
            {
                if(pwd[i] > 0 && pwd[i] < 7) //21~26
                {
                    if(i > 1)
                        dp[i] = dp[i-1] + dp[i-2];
                    else
                        dp[i] = 2;
                }else if (pwd[i] == 0) {//20
                    if(i > 1)
                        dp[i] = dp[i-2];
                    else
                        dp[i] = 1;
                }else{//27~29
                    dp[i] = dp[i-1];
                }
            }else if(pwd[i] == 0){//30, 40, 50, 60,70,80, 90
                dp[i] = 0;
            }else{
                dp[i] = dp[i-1];
            }     
        }
        //int solution = dp[length-1] % 1000000;
        int solution = dp[length-1];

        return Integer.toString(solution);        
    }

}