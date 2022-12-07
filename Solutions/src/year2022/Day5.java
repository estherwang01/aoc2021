package year2022;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

public class Day5 {
    public static void main(String[] args) {
        try {
            File day1 = new File("Solutions\\data22\\day5input");
            BufferedReader br = new BufferedReader(new FileReader(day1));
            String line = br.readLine();
            ArrayList<ArrayList<String>> stuff = new ArrayList<>(9);
            for(int i =0; i<9; i++){
                stuff.add(new ArrayList<>());
            }

            while(line != null && !line.equals("")){
                for(int i =0; i<line.length(); i++){
                    if(line.charAt(i) != ' '){
                        stuff.get(i/4).add(line.substring(i+1, i+2));
                    }
                    i+=3;
                }
                line = br.readLine();
            }
            line = br.readLine();
            while(line != null){
                String[] s = line.split(" ");
                int num = Integer.parseInt(s[1]);
                int from = Integer.parseInt(s[3])-1;
                int to = Integer.parseInt(s[5])-1;
                for(int i =0; i<num; i++){
                    String e = stuff.get(from).remove(num-i-1);
                    stuff.get(to).add( 0,e);
                }
                line = br.readLine();
            }

            for(int i =0; i<9; i++){
                System.out.print(stuff.get(i).get(0));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
