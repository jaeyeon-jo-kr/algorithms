
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.stream.IntStream;


//https://www.acmicpc.net/problem/1157
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String word = reader.readLine();
        reader.close();
        int[] counts = IntStream.rangeClosed('A', 'Z')
                .map(i -> (int)word.chars().filter(ch -> ch == i || ch == i + ('a' - 'A')).count()).toArray();        
        int max = IntStream.of(counts).max().getAsInt();
        int max_index = (int)IntStream.of(counts).takeWhile(i -> i < max).count();
        boolean duplicated = IntStream.of(counts).filter(i -> i == max).count() > 1;
        
        if(duplicated){
            System.out.println('?');
            return;
        }
        
        System.out.println((char)('A' + max_index));
    }

}