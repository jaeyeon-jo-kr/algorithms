
import java.util.stream.*;
import java.util.*;

// https://www.acmicpc.net/problem/1152

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String problem = scanner.nextLine();
        scanner.close();

        int space_count = (int)problem.chars().filter(i -> i == ' ').count();
        int word_count = space_count + 1;

        if(problem.charAt(0) == ' ')        
            word_count--;
        
        if(problem.charAt(problem.length()-1) == ' ')
            word_count--;

        System.out.println(word_count);
    }

}