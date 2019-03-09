import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();

        int stair[] = new int[n + 1];
        stair[0] = 0;
        for (int i = 1; i <= n; i++) {
            stair[i] = scan.nextInt();
        }

        int dp[] = new int[n + 1];
        dp[0] = 0;
        if (n >= 1)
            dp[1] = stair[1];

        if (n >= 2)
            dp[2] = stair[1] + stair[2];        

        for (int i = 3; i <= n; i++) {
            int max = Math.max(dp[i-2], dp[i - 3] + stair[i-1]);
            dp[i] = stair[i] + max;
        }
        System.out.println(dp[n]);
        scan.close();
    }
}