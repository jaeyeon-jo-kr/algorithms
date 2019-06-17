
import java.util.Scanner;
// https://www.acmicpc.net/problem/9498

class Main {

    static public String getGrade(int n)
    {
        if(90 <= n && n <= 100)
            return "A";
        if(80 <= n && n <= 89)
            return "B";
        if(70 <= n && n <= 79)
            return "C";
        if(60 <= n && n <= 69)
            return "D";
        
        return "F";
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();
        System.out.println(getGrade(n));
    }

}