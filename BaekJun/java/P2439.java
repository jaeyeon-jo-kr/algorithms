
import java.util.Scanner;
import java.util.stream.*;

//https://www.acmicpc.net/problem/2439

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();        
        // for(int i = 1; i<=n ;i++)
        // {
        //     for(int j = 1; j<= n - i; j++)
        //     {
        //         System.out.print(' ');
        //     }

        //     for(int j = 1; j <= i; j++)
        //     {
        //         System.out.print('*');
        //     }
        //     System.out.println();
        // }
        //Change to Intstream
        // for(int i = 1; i<=n ;i++)
        // {
        //     IntStream.range(0, n - i).forEach( j -> System.out.print(' ') );
        //     IntStream.range(0, i).forEach( j -> System.out.print('*') );
        //     System.out.println();
        // }
        IntStream.range(1, n + 1).forEach(i -> {
            IntStream.range(0, n - i).forEach( j -> System.out.print(' ') );
            IntStream.range(0, i).forEach( j -> System.out.print('*') );
            System.out.println();
        });
        scanner.close();
    }

}