
import java.util.stream.*;
import java.util.*;

// https://www.acmicpc.net/problem/1924

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int _mon = scanner.nextInt();
        int _day = scanner.nextInt();        
        scanner.close();

        HashMap <Integer, String> day_list = new HashMap<>();
        day_list.put(0,"SUN");
        day_list.put(1,"MON");
        day_list.put(2,"TUE");
        day_list.put(3,"WED");
        day_list.put(4,"THU");
        day_list.put(5,"FRI");
        day_list.put(6,"SAT");

        HashMap <Integer, Integer> day_per_month = new HashMap<>();
        day_per_month.put(1, 31);
        day_per_month.put(3, 31);
        day_per_month.put(5, 31);
        day_per_month.put(7, 31);
        day_per_month.put(8, 31);
        day_per_month.put(10, 31);
        day_per_month.put(12, 31);

        day_per_month.put(4, 30);
        day_per_month.put(6, 30);
        day_per_month.put(9, 30);
        day_per_month.put(11, 30);
        
        day_per_month.put(2, 28);

        int day_status = 0;
        boolean found = false;

        for(int month=1;month<=12;month++){            
            for(int day = 1; day <= day_per_month.get(month); day++){
                day_status = (day_status + 1) % 7;

                if(_mon == month && _day == day)
                {
                    System.out.println(day_list.get(day_status));
                }
            }
        }

    }

}