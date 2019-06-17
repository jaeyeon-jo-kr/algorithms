
import java.util.stream.*;
import java.util.stream.IntStream.Builder;
import java.util.*;

// https://www.acmicpc.net/problem/10039

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);        
        int average = (int)IntStream.range(0, 5).map(i-> {
            int score = scanner.nextInt();
            if(score < 40)
                return 40;
            return score;
        }).average().getAsDouble();
        scanner.close();
        System.out.println(average);
    }

}