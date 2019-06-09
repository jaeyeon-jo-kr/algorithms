
import java.util.Scanner;
import java.util.stream.*;

//https://www.acmicpc.net/problem/2440

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();        
       
        IntStream.range(0, n).forEach(i -> {
            IntStream.range(0, n-i).forEach( j -> System.out.print('*') );
            System.out.println();
        });
        scanner.close();
    }

}