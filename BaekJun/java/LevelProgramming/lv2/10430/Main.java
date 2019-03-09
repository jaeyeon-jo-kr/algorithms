import java.util.Scanner;

public class Main{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String numbers = sc.nextLine();
        sc.close();     
        String[] numArray = numbers.split(" ");

        int a = Integer.parseInt(numArray[0]);
        int b = Integer.parseInt(numArray[1]);
        int c = Integer.parseInt(numArray[2]);

        System.out.println((a+b) % c);
        System.out.println(((a%c) + (b%c))%c);
        System.out.println((a*b)%c);
        System.out.println(((a%c) * (b%c))%c);
    }
}

