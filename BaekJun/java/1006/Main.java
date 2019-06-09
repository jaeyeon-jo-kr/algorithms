import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    static public void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int testCount = scanner.nextInt();
        int rowCount = scanner.nextInt();
        int memberCount = scanner.nextInt();

        /**
         * 1 0 3   
         * 2 0 4 
         * 
         * CASE 0(1) : 1 0 3  0 : +1(2) 2: +1 3: +1 4: +1(2) 5: +1(2) 6: +2 7: +1(2) 8: +1 9: +1
         *             2 0 4 
         * 
         * CASE 2 :    1 0 3 
         *             0-0 4 
         * 
         * CASE 3 :    0-0 3 
         *             2 0 4 
         * 
         * CASE 4 :    1 0-0 
         *             2 0 4 
         * 
         * CASE 5 :    1 0 3 
         *             2 0-0 
         * 
         * CASE 6 :    0-0 3 
         *             0-0 4 
         * 
         * CASE 7 :    1 0-0 
         *             2 0-0 
         * 
         * CASE 8 :    1 0-0 
         *             0-0 4 
         * 
         * CASE 9 :    0-0 3 
         *             1 0-0 
         */

        final int OUTER = 0;
        final int INNER = 1;
        final int WHOLE = 2;

        List<List<Integer>> map = new ArrayList<>();
        map.add(new ArrayList<>());
        map.add(new ArrayList<>());


        IntStream.range(0, rowCount).forEach(e -> map.get(OUTER).add(scanner.nextInt()));
        IntStream.range(0, rowCount).forEach(e -> map.get(INNER).add(scanner.nextInt()));
        scanner.close();

        //map.stream().forEach(e -> e.stream().forEach(f-> System.out.println(f)));
        List<List<Integer>> dp = new ArrayList<>();        
        dp.add(OUTER, new ArrayList<Integer>());
        dp.add(INNER, new ArrayList<Integer>());
        dp.add(WHOLE, new ArrayList<Integer>());

        dp.get(OUTER).add(0,1);
        dp.get(INNER).add(0,1);
        dp.get(WHOLE).add(0, map.get(OUTER).get(0) + map.get(INNER).get(0) <= memberCount?1:2);

        
        for(int i = 1; i < rowCount; i++){
            
            if(map.get(OUTER).get(i-1) + map.get(OUTER).get(i) <= memberCount)
            {
                int innerBlock = Math.min(dp.get(INNER).get(i-1) + 1, dp.get(WHOLE).get(i-1) + 1);
                dp.get(OUTER).add(i, innerBlock);
            }else{
                dp.get(OUTER).add(i, dp.get(WHOLE).get(i-1) + 1);
            }

            if(map.get(INNER).get(i-1) + map.get(INNER).get(i) <= memberCount)
            {
                int outerBlock = Math.min(dp.get(OUTER).get(i-1) + 1, dp.get(WHOLE).get(i-1) + 1);
                dp.get(INNER).add(i, outerBlock);
            }else{
                dp.get(INNER).add(i, dp.get(WHOLE).get(i-1) + 1);
            }
             
            
            System.out.println("Inner["+ i + "] : " + dp.get(INNER).get(i));
            System.out.println("Outer["+ i + "] : " + dp.get(OUTER).get(i));

            int lastColumn = map.get(INNER).get(i) + map.get(OUTER).get(i) <= memberCount ? 1:2;
            lastColumn += dp.get(WHOLE).get(i-1);
            System.out.println("LastCol["+ i + "] : " + lastColumn);

            int lastTwoRows = Integer.MAX_VALUE;
            if( map.get(INNER).get(i-1) + map.get(INNER).get(i) <= memberCount &&
                map.get(OUTER).get(i-1) + map.get(OUTER).get(i) <= memberCount){
                    lastTwoRows = 2;
                if(i>1){
                    lastTwoRows += dp.get(WHOLE).get(i-2);
                }
            }
            System.out.println("LastTwoRows["+ i + "] : " + lastTwoRows);
            int whole = Math.min(dp.get(OUTER).get(i) + 1 , dp.get(INNER).get(i) + 1);
            whole = Math.min(lastColumn, whole);            
            whole = Math.min(lastTwoRows, whole);
            dp.get(WHOLE).add(i,whole);
            System.out.println("Whole["+ i + "] : " + whole);
            System.out.println("");

        }

        System.out.println(dp.get(WHOLE).get(rowCount-1));
    }
}