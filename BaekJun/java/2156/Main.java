import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        //int n = 10000;
        
        int cost[] = new int[n+1];
        long dp[][] = new long[n+1][4];
        for(int i=1;i<=n;i++)
        {
            cost[i] = scanner.nextInt();
            //cost[i] = 1000;
        }
        scanner.close();

        for(int i=1;i<=n;i++)
        {
            if(i == 1){
                dp[1][0] = 0; // X, X
                dp[1][1] = cost[1];// X, O
                dp[1][2] = cost[1];// O,O
                dp[1][3] = 0;// O, X            
            } else {                
                dp[i][0] = Math.max(dp[i-1][0], dp[i-1][3]); // (O,X), (X,X) ->X, X
                dp[i][1] = Math.max(dp[i-1][0], dp[i-1][3]) + cost[i]; // (O,X) , (X,X),-> X, O 
                dp[i][2] = dp[i-1][1] + cost[i]; // X,O -> O, O                
                dp[i][3] = Math.max(dp[i-1][1], dp[i-1][2]); //O,O, X,O -> O,X

            }
            // System.out.println("phase 1(X,O,O) dp[" + (i) + "] : " + dp[i][0]);
            // System.out.println("phase 2(O,X,O) dp[" + (i) + "] : " + dp[i][1]);                
            // System.out.println("phase 3(O,O,X) dp[" + (i) + "] : " + dp[i][2]);
            // System.out.println("phase 4(X,O,X) dp[" + (i) + "] : " + dp[i][3]);
            // System.out.println();
        }
        long max = 0; 
        for(int i=0;i<4;i++)
        {
            max = Math.max(dp[n][i], max);
        }
        
        System.out.println(max);
    }
}
