
import java.util.Scanner;
// https://www.acmicpc.net/problem/10817

class Main {
    public static boolean isMiddle(int a, int b, int c)
    {
        if((a <= b && b <= c) || (c <= b && b <= a))
            return true;
        return false;
    }
    public static int getMiddle(int a, int b, int c)
    {
        if(isMiddle(a,b,c))
            return b;
        if(isMiddle(a,c,b))
            return c;
        if(isMiddle(b, a, c))
            return a;
        return -1;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        scanner.close();
        System.out.println(getMiddle(a, b, c));
    }

}