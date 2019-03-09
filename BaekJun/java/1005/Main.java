import java.util.Scanner;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static int find(int n, int k, int[] cost, boolean[][] rule, int[] link, int w) {
        int[] dp = new int[n];
        Arrays.fill(dp, 0);

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (link[i] == 0){
                queue.add(i);
                dp[i] = cost[i];
            }
        }

        for (int i = 0; i < n; i++) {
            int start = queue.remove();

            for (int j = 0; j < n; j++) {
                if (rule[start][j] == true) {                    
                    link[j]--;
                    dp[j] = Math.max(dp[j], cost[j] + dp[start]);
                    if (link[j] == 0)
                        queue.add(j);
                }
            }
        }

        return dp[w - 1];
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int testCase = scan.nextInt();
        int solution[] = new int[testCase];
        for (int i = 0; i < testCase; i++) {
            int n = scan.nextInt();
            int k = scan.nextInt();

            int[] cost = new int[n];
            for (int j = 0; j < n; j++) {
                cost[j] = scan.nextInt();
            }

            boolean[][] rule = new boolean[n][n];
            for (int j = 0; j < n; j++) {
                Arrays.fill(rule[j], false);
            }
            int[] link = new int[n];
            for (int j = 0; j < k; j++) {
                int row = scan.nextInt() - 1;
                int col = scan.nextInt() - 1;
                rule[row][col] = true;
                link[col]++;
            }
            int w = scan.nextInt();
            
            solution[i] = find(n, k, cost, rule, link, w);
        }

        for(int i=0;i<testCase;i++){
            System.out.println(solution[i]);
        }
        scan.close();
        
    }
}