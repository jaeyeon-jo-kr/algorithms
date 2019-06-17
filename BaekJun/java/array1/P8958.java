
import java.util.stream.*;
import java.util.*;

// https://www.acmicpc.net/problem/8958

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int c = scanner.nextInt();
        scanner.nextLine();
        ArrayList<String> problems = new ArrayList<>();
        IntStream.rangeClosed(1, c).forEach(i -> {
            String problem = scanner.nextLine();            
            problems.add(problem);
        });
        scanner.close();

        problems.forEach(problem -> {
            int total_score = 0;
            int current_score = 0;
            for (int i = 0; i < problem.length(); i++) {
                char answer = problem.charAt(i);
                if(answer == 'O'){
                    current_score ++;
                    total_score += current_score;
                    continue;
                }
                current_score = 0;
            }
            System.out.println(total_score);
        });

    }

}