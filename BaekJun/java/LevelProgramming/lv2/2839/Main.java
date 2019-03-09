import java.util.Scanner;

public class Main{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        sc.close();

        int result = -1;

        for(int i=0;i*3<=a;i++)
        {
            if((a- i*3) % 5 ==0)
            {
                result = i + ((a-i*3) /5);
                break;
            }
        }
        System.out.println(result);
       

    }
}

