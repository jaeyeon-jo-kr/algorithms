import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int m = scan.nextInt();// height
        int n = scan.nextInt();// width
        int cost[][] = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                cost[j][i] = scan.nextInt();
            }
        }
        scan.close();

        
        class Spot {
            public Spot(int x, int y) {
                this.x = x;
                this.y = y;
            }

            public int x, y;
        }

        Stack<Spot> stack = new Stack<>();
        stack.push(new Spot(0, 0));
        int visitCnt[][] = new int[n][m];
        int dp[][] = new int[n][m];
        dp[n-1][m-1] = 1;
        
        int temp = 0;
        while (!stack.isEmpty()) {
            Spot current = stack.pop();
            int j = current.x;
            int i = current.y;

            cost[j][i] += temp;
            temp=0;
            System.out.println();
            System.out.print("(" + (j) + "," + (i) + ")(" + dp[j][i] + ") -> ");
            
            if (i - 1 >= 0 && cost[j][i - 1] < cost[j][i] && visitCnt[j][i] <m*n) {                
                stack.add(new Spot(j, i - 1));
                temp = cost[j][i-1];
                System.out.print("(" + (j) + "," + (i-1) + ") ");
            }

            if (i + 1 < m && cost[j][i + 1] < cost[j][i] && visitCnt[j][i] < m*n) {                
                stack.add(new Spot(j, i + 1));
                temp = cost[j][i+1];
                System.out.print("(" + (j) + "," + (i+1) + ") ");
            }

            if (j - 1 >= 0 && cost[j - 1][i] < cost[j][i] && visitCnt[j][i] < m*n ) {                
                stack.add(new Spot(j - 1, i));
                temp = cost[j-1][i];
                System.out.print("(" + (j - 1) + "," + (i) + ") ");
            }

            if (j + 1 < n && cost[j + 1][i] < cost[j][i]  && visitCnt[j][i] < m*n) {
                stack.add(new Spot(j + 1, i));
                temp = cost[j+1][i];
                System.out.print("(" + (j + 1) + "," + (i) + ") ");
            }
        }

        System.out.println(dp[0][0]);

    }
}