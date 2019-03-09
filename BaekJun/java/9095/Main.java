import java.util.Scanner;

public class Main{

    public static void main(String [] args)
    {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int tc[] = new int[n];
        for(int i=0;i<n;i++){
            tc[i] = scanner.nextInt();
        }
        scanner.close();

        int dp[] = new int[11];

        for(int i=0;i<11;i++)
        {
            if(i == 0)
            {
                dp[i] = 1;
            }else if(i == 1)
            {
                dp[i] = 2;
            }else if(i == 2)
            {
                dp[i] = 4;
            }else {
                dp[i] = dp[i-3] + dp[i-2] + dp[i-1];
            }
        }

        for(int i=0;i<n;i++){
            System.out.println(dp[tc[i]-1]);
        }        

    }   

}