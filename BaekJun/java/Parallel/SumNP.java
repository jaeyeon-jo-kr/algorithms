import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.stream.IntStream;

public class SumNP extends Thread{
    public static int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8 };
    public static int[] msg = { -1, -1, -1, -1, -1,-1,-1,-1};
    public static boolean finish = false;
    private int core = -1;
    public SumNP(int core)
    {
        this.core = core;
    }

    public void run()
    {
        int divisor = 2;
        while(divisor<=8)
        {
            int diff = divisor / 2;
            if(core % divisor == 0)
            {
                while(msg[core + diff] == -1){}
                arr[core] += msg[core + diff];
                msg[core + diff] = -1;
                System.out.printf("arr[%d] + msg[%d] = %d(arr[%d]) \n", core, core + diff, arr[core], core);
            }else if((core - diff) % divisor == 0){
                msg[core] = arr[core];
            }
            divisor *= 2;
        }
        
    }

    public static void main(String[] args) {
        ArrayList<Sum> threads = new ArrayList<>();
        long before = Calendar.getInstance().getTimeInMillis();
        int sum  = 0;
        for(int i=0;i<8;i++)
        {
            sum += arr[i];
        }
        long time = Calendar.getInstance().getTimeInMillis() - before;
        System.out.print("time : ");
        System.out.print(time);
        System.out.println(" ms");

        System.out.println(sum);

    }
}