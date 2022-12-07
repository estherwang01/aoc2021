package year2022;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Day6 {
    public static void main(String[] args) {
        try {
            File day1 = new File("Solutions\\data22\\day6input");
            BufferedReader br = new BufferedReader(new FileReader(day1));
            String line = br.readLine();
            List<Character> c = new ArrayList<>();
            for(Character ch: line.toCharArray()){
                c.add(ch);
            }
            for(int i =0; i<c.size()-13; i++){
                List<Character> view = c.subList(i, i+14);
                Set<Character> s = new HashSet<>(view);
                if(view.size() == s.size()){
                    System.out.println(i+14);
                    return;
                }
            }


        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
