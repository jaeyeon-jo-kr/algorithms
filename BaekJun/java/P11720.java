import java.util.stream.*;
import java.util.*;

//https://www.acmicpc.net/problem/11720
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String total_number = scanner.nextLine();
        String char_list = scanner.nextLine();
        scanner.close();
        int sum = char_list.chars().map(value -> value - '0').sum();
        System.out.println(sum);
    }
}