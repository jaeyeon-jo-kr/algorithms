
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.*;
// https://www.acmicpc.net/problem/4673

class Main {
    
    public static void main(String[] args) {
        int [] array = new int[10000];
        
        
        Stream.iterate(1,i -> i<10000, i -> i+ 1)
            .mapToInt(i -> d(i))
            .filter(i->i<10000 && i>0)//.forEach(System.out::println);
            .forEach(i -> {array[i] = 1;});
        
        Stream.iterate(1, i -> i <10000, i -> i+1)
            .filter(i->array[i] != 1).forEach(System.out::println);
            
    }
    public static int d(int n)
    {    
        int m0 = n/1000;
        int m1 = (n % 1000)/100;
        int m2 = (n % 100)/10;
        int m3 = (n % 10);
        return n+ m0 + m1 + m2 + m3;
    }

}