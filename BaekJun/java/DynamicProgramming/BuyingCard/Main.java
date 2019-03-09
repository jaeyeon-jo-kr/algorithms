
import java.util.Scanner;
import java.lang.Math;

public class Main {

    public static void solution(String nStr, String pStr)
    {
        int n = Integer.parseInt(nStr);
        int p[] = new int[n + 1];
        int pIndex = 0;
        for(String e : pStr.split(" "))
        {
            p[pIndex ++] = Integer.parseInt(e);            
        }

        int [] dp = new int[n];
        dp[0] = p[0];

        for(int i=1;i<n;i++)
        {
            int max = p[i];
            //System.out.println("current : " + i);
            for(int j=0;j<i;j++){
                //System.out.println("compare at : dp[" + (i-j-1) + "] + p[" + (j) + "], p[" + i +"]");
                
                max = Math.max(dp[i-j-1] + p[j], max);
            }
            dp[i] = max;
        }

        System.out.println(dp[n-1]);
    }

    //10
    public static void test1()
    {
        System.out.println("Test1");
        solution("4","1 5 6 7");
    }

    //50
    public static void test2()
    {
        System.out.println("Test2");
        solution("5","10 9 8 7 6");

    }

    //55
    public static void test3()
    {
        System.out.println("Test3");
        solution("10","1 1 2 3 5 8 13 21 34 55");

    }
    //50
    public static void test4()
    {
        System.out.println("Test4");
        solution("10","5 10 11 12 13 30 35 40 45 47");

    }

    //20
    public static void test5()
    {
        System.out.println("Test5");
        solution("4","5 2 8 10");

    }
    //18
    public static void test6()
    {
        System.out.println("Test6");
        solution("4","3 5 15 16");

    }



    public static void main(String []args)
    {
        Scanner sc = new Scanner(System.in);
        String nStr = sc.nextLine();
        String pStr = sc.nextLine();
        solution(nStr,pStr); 
        sc.close();
        // test1();
        // test2();       
        // test3();
        // test4();       
        // test5();
        // test6();       
    }
}