import java.util.Scanner;
//https://www.acmicpc.net/problem/2448
class Main{
    public static String[] getBasicTriangle()
    {
        String [] triangle = new String[3];
        triangle[0] = "  *  ";
        triangle[1] = " * * ";
        triangle[2] = "*****";
        return triangle;
    }

    public static String[] extendTriangle(final String [] previousTriangle)
    {
        int previousWidth = previousTriangle[0].length();    
        int extendedWidth = previousWidth * 2 + 1;
        int previousHeight = previousTriangle.length;
        String [] extendedTriangle = new String[ previousHeight * 2];

        int wingSpaceWidth = (extendedWidth - previousWidth)/2;
        
        StringBuilder sBuilder = new StringBuilder();
        for(int i=0;i<wingSpaceWidth;i++)
        {
            sBuilder.append(' ');
        }
        String wingSpace = sBuilder.toString();

        for(int i=0;i<previousTriangle.length;i++)
        {
            extendedTriangle[i] = wingSpace + previousTriangle[i] + wingSpace;
            extendedTriangle[i + previousHeight] = previousTriangle[i] + ' ' + previousTriangle[i];
        }
        return extendedTriangle;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();
        String [] out = null;
        for(int i = 3; i <= n;i *= 2)
        {
            if(i == 3)
            {   
                out = getBasicTriangle();
                continue;
            }
            out = extendTriangle(out);
        }

        for (String str : out) {
            System.out.println(str);
        }
    }
}