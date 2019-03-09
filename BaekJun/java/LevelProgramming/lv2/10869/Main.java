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
        System.out.println(a+b);  
        System.out.println(a-b);  
        System.out.println(a*b);  
        System.out.println(a/b);  
        System.out.println(a%b);  
        
        
        
    }
}