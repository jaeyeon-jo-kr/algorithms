import java.util.stream.*;
import java.util.*;

//https://www.acmicpc.net/problem/11721
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String char_list = scanner.nextLine();        
        scanner.close();
        int i=0;
        for(i=0;i<char_list.length();i++)
        {
            if(i % 10 == 9 && i >0)
                System.out.println(char_list.substring(i-9, i+1));
        }
        System.out.println(char_list.substring(i - (i % 10), i));
    }
}