import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int value[][] = new int[n][3];
        
        for(int i=0;i<n;i++)
        {
            value[i][0] = scan.nextInt();
            value[i][1] = scan.nextInt();
            value[i][2] = scan.nextInt();
        }

        int dp[][] = new int[2][3];
        dp[0][0] = value[0][0];
        dp[0][1] = value[0][1];
        dp[0][2] = value[0][2];

        for(int i=1; i<n; i++)
        {
            dp[1][0] = Math.min(dp[0][1], dp[0][2]) + value[i][0];
            dp[1][1] = Math.min(dp[0][0], dp[0][2]) + value[i][1];
            dp[1][2] = Math.min(dp[0][0], dp[0][1]) + value[i][2];

            dp[0][0] = dp[1][0];
            dp[0][1] = dp[1][1];
            dp[0][2] = dp[1][2];

        }

        int min = Math.min(dp[1][0], dp[1][1]);
        min = Math.min(dp[1][2], min);


        System.out.println(min);
        scan.close();
        
    }

}