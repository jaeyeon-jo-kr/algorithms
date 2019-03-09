
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        scan.close();
        long[][] dp = new long[n][10];
        dp[0][0] = 0;
        for(int i = 1;i<=9 ;i++){
            dp[0][i] = 1;
        }

        
        for(int i = 1; i<n; i++){
            dp[i][0] = dp[i-1][1];
            dp[i][9] = dp[i-1][8];
            
            for(int j=1;j<=8;j++){
                dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % 1000000000;
            }
        }

        long count = 0;
        for(int i=0;i<10;i++)
        {
            count += dp[n-1][i];
            count = count % 1000000000;
        }
        System.out.println(count);
    }
}