
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.*;
//https://www.acmicpc.net/problem/4344
class Main {
    public static void printAvg(List<Integer> scoreList)
    {
        double average = scoreList.stream().mapToDouble(i -> (double)i).average().getAsDouble();
        double all = (double)scoreList.size();
        double count = (double)scoreList.stream().mapToDouble(i->(double)i).filter(i -> i > average).count();
        double percent = count/all * 100.0;
        System.out.println(String.format("%.3f%%", percent));
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int c = scanner.nextInt();        
        ArrayList<ArrayList<Integer>> problemSpace = new ArrayList<>();
        IntStream.range(0, c).forEach(i -> {
            int n = scanner.nextInt();
            ArrayList<Integer> scoreList = new ArrayList<>();
            IntStream.range(0, n).forEach(j -> {                
                var score = scanner.nextInt();
                scoreList.add(score);
            });
            problemSpace.add(scoreList);
        });

        problemSpace.stream().forEach(scoreList -> {
            printAvg(scoreList);
        });
        
        scanner.close();
    }

}