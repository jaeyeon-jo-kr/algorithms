
import java.util.stream.*;
import java.util.*;

// https://www.acmicpc.net/problem/2577

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();

        Long multi = (long)a * b * c;
        String resultString = Long.toString(multi);

        IntStream.rangeClosed(0, 9).forEach(i -> {
            System.out.println(
                resultString.chars().filter(j -> j == '0' + i).count());
        });

    }

}