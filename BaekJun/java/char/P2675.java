import java.io.*;
import java.util.*;
import java.util.stream.*;

//https://www.acmicpc.net/problem/10809
class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for(int i=0;i<n;i++){
            int count = scanner.nextInt();
            String str = scanner.nextLine().trim();
            String out = "";
            for(int j=0;j<str.length();j++)
            {
                for(int k=0;k<count;k++){
                    out += str.charAt(j);                   
                }
            }
            System.out.println(out);
        }
        scanner.close();
    }

}