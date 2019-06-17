
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;
//https://www.acmicpc.net/problem/11654

class Main {
    public static void main(String[] args) throws IOException{
        BufferedInputStream inputStream = new BufferedInputStream(System.in);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int ch = inputStream.read();
        char [] cBuff = String.format("%d", ch).toCharArray();
        writer.write(cBuff);
        inputStream.close();
        writer.flush();
        writer.close();
    }

}