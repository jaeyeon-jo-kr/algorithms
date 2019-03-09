import java.util.Scanner;
import java.lang.Math;
import java.util.Arrays;

public class Main{
    
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String nStr = sc.nextLine();
        sc.close();
        int number = Integer.parseInt(nStr);

        int dp[] = new int[number];        
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[number-1] = 0;


        for(int i = number-1;i>=0;i--)
        {
            int n = i+1;

            if(n % 3 == 0){
                
                dp[n/3-1] = Math.min(dp[i] + 1, dp[n/3-1]);
            }

            if(n%2 ==0 ){
                dp[n/2-1] = Math.min(dp[i] + 1, dp[n/2-1]);
            }

            
            if(n > 1 ){
                dp[n-2] = Math.min(dp[i] + 1, dp[n-2]);
            }

        }
        System.out.println(dp[0]);
    }

   
}