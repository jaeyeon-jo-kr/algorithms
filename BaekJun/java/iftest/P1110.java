
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.*;
// https://www.acmicpc.net/problem/1110

class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();
        solution(n);
    }
    public static void solution(int n)
    {
        if(n < 10)
            n *= 10;
        
        int cycle = 0;
        int current = n;
        int next = 0;
        int max_cycle = 100;
        do{ 
            int a = current % 10;
            int b = current /10;
            next = a*10 + ((a+b) % 10);
            cycle++;
            current = next;
        }while(n !=  next && cycle < max_cycle);        
        System.out.println(cycle);
    }

}