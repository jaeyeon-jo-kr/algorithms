import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        
        int [][] dp = new int[2][n];

        int totalMax = 0;
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < i+1; j++){                
                int value = scanner.nextInt();                
                if(i > 0){
                    int max = 0;
                    if(j==0)
                        max = dp[0][0];
                    else if(j == i)
                        max = dp[0][j-1];
                    else 
                        max = Math.max(dp[0][j-1], dp[0][j]);
                    dp[1][j] = max + value;
                }else{
                    dp[1][j] = value;
                }
                totalMax = Math.max(totalMax, dp[1][j]);
            }
            dp[0] = Arrays.copyOf(dp[1], n);
        }
        scanner.close();
        System.out.println(totalMax);

    }
}