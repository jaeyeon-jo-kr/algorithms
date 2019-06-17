
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.*;
//https://www.acmicpc.net/problem/1546
class Main {
    public static double newAvg(List<Integer> scoreList)
    {
        double max = (double)Collections.max(scoreList); 
        var avg = scoreList.stream().mapToDouble(i->(i/max)*100).average();
        return avg.getAsDouble();        
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();        
        ArrayList<Integer> scoreList = new ArrayList<>();
        IntStream.range(0, n).forEach(i -> {
            int in = scanner.nextInt();
            scoreList.add(in);
        });
        
        scanner.close();
        System.out.println(newAvg(scoreList));
    }

}