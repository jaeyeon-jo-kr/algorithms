
import java.util.Scanner;
//https://www.acmicpc.net/problem/1065
class Main {



    public static boolean isHanSu(int n)
    {
        int m0 = n/1000;
        int m1 = (n % 1000)/100;
        int m2 = (n % 100)/10;
        int m3 = (n % 10);

        if(m0 == 1)
            return false;

        if(m1 > 0){ 
            if(m1 - m2 == m2 - m3)
                return true;
            return false;
        }
        return true;

    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();        
        scanner.close();

        int hansuCnt = 0;

        for(int i=1;i<=n;i++){
            if(isHanSu(i))
                hansuCnt += 1;
        }

        System.out.println(hansuCnt);
    }
    
}