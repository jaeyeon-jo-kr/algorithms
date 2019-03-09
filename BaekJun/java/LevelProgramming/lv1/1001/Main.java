import java.util.Scanner;

public class Main{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String numbers = sc.nextLine();
        
        String[] numArray = numbers.split(" ");

        int sum = Integer.parseInt(numArray[0]) - Integer.parseInt(numArray[1]);
        System.out.println(sum);  
        sc.close();     
        
    }
}