package year2022;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.PriorityQueue;

public class Day1 {
    public static void main(String[] args) {
        try {
            File day1 = new File("Solutions\\data22\\day1input");
            BufferedReader br = new BufferedReader(new FileReader(day1));
            String line = br.readLine();
            int total = 0;
            PriorityQueue<Integer> top = new PriorityQueue<>();
            int max = 0;
            while(line != null){
                if(line.equals("")){
                    top.add(-total);
                    total = 0;
                } else {
                    total += Integer.parseInt(line);
                }
                line = br.readLine();
            }
            int count = 0;
            for(int i =0; i<3; i++){
                count += top.poll();
            }
            System.out.println(count);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
