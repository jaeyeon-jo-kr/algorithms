import java.util.Scanner;

public class Main{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String numbers = sc.nextLine();
        sc.close();     
        String[] numArray = numbers.split(" ");


        double a = Double.parseDouble(numArray[0]);
        double b = Double.parseDouble(numArray[1]);

        if(b == 0){
            return;            
        }
        double div = a/b;
        System.out.println(div);  
        
        
    }
}