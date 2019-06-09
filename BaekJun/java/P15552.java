import java.util.stream.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.util.*;

//https://www.acmicpc.net/problem/15552
class Main {
    public static void main(String[] args) throws IOException {        
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String nStr = bufferedReader.readLine();
        int n = Integer.parseInt(nStr);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i=0;i<n;i++)
        {
            String problem = bufferedReader.readLine();
            String problemsStr[] = problem.split(" ");
            int left = Integer.parseInt(problemsStr[0]);
            int write = Integer.parseInt(problemsStr[1]);
            int solution = left + write;
            String solutionStr = Integer.toString(solution) + "\n";
            bufferedWriter.write(solutionStr);
        }
        bufferedReader.close();        
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}