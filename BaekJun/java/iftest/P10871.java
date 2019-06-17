
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.*;
// https://www.acmicpc.net/problem/10871

class Main {
    
    public static String getLower(List<Integer> intList, final int x)
    {
        ArrayList<String> results = new ArrayList<>();

        intList.stream().filter(i -> i < x).forEach(i-> results.add(Integer.toString(i)));
        
        return String.join(" ", results);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int x = scanner.nextInt();
        ArrayList<Integer> intList = new ArrayList<>();
        IntStream.range(0, n).forEach(i -> {
            int in = scanner.nextInt();
            intList.add(in);
        });
        
        scanner.close();
        System.out.println(getLower(intList, x));
    }

}