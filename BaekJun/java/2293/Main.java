import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int coin[] = new int[n+1];

        for (int i = 1; i <= n; i++) {
            coin[i] = scanner.nextInt();
        }
        scanner.close();

        int dp[] = new int[k + 1];
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            // System.out.print("coin ["+ i + "] = " + coin[i] + "\t");
            for (int j = coin[i]; j <= k; j++) {                
                // System.out.print("coin[j] == " + coin[i] + "\t");
                // System.out.print("dp ["+ j + "] = " + dp[j] + " + ");
                dp[j] += dp[j - coin[i]];
                // System.out.print(dp[j - coin[i]] + "\t");
            }
            
            // System.out.println();
        }

        System.out.println(dp[k]);

    }
}