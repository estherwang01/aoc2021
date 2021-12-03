package year2021;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Day1 {
    public static void main(String[] args) {
    try {
        File day1 = new File("Solutions\\data21\\day1input");
        BufferedReader br = new BufferedReader(new FileReader(day1));
        int prev = Integer.MAX_VALUE;
        String one = br.readLine();
        String two = br.readLine();
        String three = br.readLine();
        int count = 0;
        while(one != null && two != null && three != null){
            int o = Integer.parseInt(one);
            int t = Integer.parseInt(two);
            int th = Integer.parseInt(three);
            int n = o + t + th;
            if(n > prev){
                count++;
            }
            prev = n;
            String next = br.readLine();
            one = two;
            two = three;
            three = next;
        }
        System.out.println(count);
    }catch(Exception e){
        e.printStackTrace();
    }

    }
}
