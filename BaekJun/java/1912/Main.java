import java.util.Scanner;
import java.lang.Math;
import java.util.Arrays;

public class Main{
    
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String nStr = sc.nextLine();
        String numbersStr = sc.nextLine();
        sc.close();        
       
        int n = Integer.parseInt(nStr);
        int numbers[] = new int[n];

        String [] list = numbersStr.split(" ");

        for (int i=0;i<n;i++) {
            numbers[i] = Integer.parseInt(list[i]);            
        }    
        int dp[]  = new int[2];
        dp[0] = numbers[0];
        int max = numbers[0];

        for(int i=1;i<n;i++)
        {
            dp[i%2] = Math.max(dp[(i-1)%2] + numbers[i], numbers[i]);
            max = Math.max(max, dp[i%2]);
        }

        System.out.println(max);
    }

   
}