// 10 T
// 5 2
// 20 8
// 30 17
// 40 24
// 50 37
// 60 52
// 70 59
// 80 73
// 90 84
// 100 90

public class NearBit {


    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int t = Integer.parseInt(input);
        int n[] = new int[t];
        int k[] = new int[t];

        int [][]dp = new int[n][n];
        dp[0][0] = 2;
        dp[1][0] = 3;
        dp[1][1] = 1;

        for(int i=2;i<n;i++){
            

        }

        for(int i=0;i<t;i++)
        {
            input = sc.nextLine();
            String [] numbers = input.split(" ");
            n[i] = Integer.parseInt(numbers[0]);
            k[i] = Integer.parseInt(numbers[1]);

            
        }

        



    }
}