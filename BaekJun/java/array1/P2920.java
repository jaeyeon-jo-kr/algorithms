
import java.util.stream.*;
import java.util.*;

// https://www.acmicpc.net/problem/2920

class Main {
    public static void main(String[] args) {
        int chord[] = new int[8];
        Scanner scanner = new Scanner(System.in);
        boolean asc = true;
        boolean des = true;
        chord[0] = scanner.nextInt();
        for(int i=1;i<8;i++)
        {
            chord[i] = scanner.nextInt();
            asc = asc & (chord[i] - chord[i-1] == 1);
            des = des & (chord[i] - chord[i-1] == -1);        
        }
        scanner.close();

        if(asc){
            System.out.println("ascending");
            return;
        }
        if(des){
            System.out.println("descending");
            return;
        }
        System.out.println("mixed");
        return;
    }

}