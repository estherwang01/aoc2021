package year2015;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Day2 {
    public static int[] calc(String[] dims){
        int l = Integer.parseInt(dims[0]);
        int w = Integer.parseInt(dims[1]);
        int h = Integer.parseInt(dims[2]);
        int one = l*w * 2;
        int two = l*h * 2;
        int three = w*h * 2;

        int smallest = Math.min(one, Math.min(two, three));

        int paper = one + two + three + (smallest/2);

        int a = (l + w) * 2;
        int b = (l+h)*2;
        int c = (w+h)*2;
        int small = Math.min(a, Math.min(b,c));
        int ribbon = small + (l*w*h);
        return new int[]{paper, ribbon};
    }
    public static void main(String[] args) {
        try {
            File day1 = new File("Solutions\\data15\\day2");
            BufferedReader br = new BufferedReader(new FileReader(day1));

            String next = br.readLine();

            int total = 0;
            int ribbon = 0;

            while(next != null){
                String[] dims = next.split("x");
                int[] calc = calc(dims);
                total += calc[0];
                ribbon += calc[1];
                next = br.readLine();
            }
            System.out.println(total);
            System.out.println(ribbon);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
