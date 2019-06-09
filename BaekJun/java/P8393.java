
import java.util.stream.*;
import java.util.*;

//      https://www.acmicpc.net/problem/8393
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();

        int sum = IntStream.rangeClosed(1, n).sum();
        System.out.println(sum);
    }

}