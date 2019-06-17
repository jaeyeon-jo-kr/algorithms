import java.io.*;
import java.util.*;
import java.util.stream.*;

//https://www.acmicpc.net/problem/10809
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String word = reader.readLine();
        reader.close();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer buffer = new StringBuffer();
        IntStream.rangeClosed((int) 'a', (int) 'z').map(word::indexOf).mapToObj(i -> String.format("%d ", i))
                .forEach(str -> buffer.append(str));
        writer.write(buffer.toString());
        writer.flush();
        writer.close();
    }

}