import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int arr[] = new int[t];
        int index = 0;
        for(int i=0;i<t;i++){
            arr[index++] = scanner.nextInt();
        }

        scanner.close();
        for (int n : arr) {
            int dp[][] = new int[2][n + 1];

            if (n >= 0) {
                dp[0][0] = 1;
                dp[1][0] = 0;
            }

            if (n >= 1) {
                dp[0][1] = 0;
                dp[1][1] = 1;
            }
            if (n >= 2) {
                for (int i = 2; i <= n; i++) {
                    dp[0][i] = dp[0][i - 1] + dp[0][i - 2];
                    dp[1][i] = dp[1][i - 1] + dp[1][i - 2];
                }
            }

            System.out.println(dp[0][n] + " " + dp[1][n]);
        }
    }
}