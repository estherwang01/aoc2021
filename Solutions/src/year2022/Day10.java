package year2022;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Day10 {
    public static void main(String[] args) {
        try {
            File day1 = new File("Solutions\\data22\\day10input");
            BufferedReader br = new BufferedReader(new FileReader(day1));
            String line = br.readLine();
            List<Integer> vals = new ArrayList<>();
            while(line!= null){
                String[] info = line.split(" ");
                vals.add(0);
                if(info.length == 2) vals.add(Integer.parseInt(info[1]));
                line = br.readLine();
            }
            int x = 1;
            String output = "";
            for(int i =0; i<vals.size(); i++){
                if(Math.abs(x - (i % 40)) <= 1) output += "#";
                else output += ".";
                x += vals.get(i);
                if((i+1) % 40 == 0) output += "\n";
            }
            System.out.println(output);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
