package year2015;

import java.io.File;
import java.util.Scanner;

public class Day8 {
    public static void main(String[] args) {
        try{
            Scanner s = new Scanner(new File("Solutions\\data15\\day8"));
            String next;
            int total = 0;
            int extra = 0;
            while(s.hasNextLine()){
                next = s.nextLine();
                total += next.length();
//                int result = process(next.substring(1, next.length()-1));
                int result = process2(next) + 2;
                extra += result;
            }
//            System.out.println(total-extra);
            System.out.println(extra);

        }catch(Exception e) {

        }

    }

    public static int process2(String s){
        int count = 0;
        for(int i =0; i<s.length(); i++){
            String c = s.substring(i, i+1);
           if(c.equals("\"") || c.equals("\\")){
               count++;
           }
        }
        return count;
    }
    public static int process(String s){
        int count = 0;
        int i;
        for(i =0; i<s.length()-1; i++){
            String c = s.substring(i, i+1);
            if(c.equals("\\") && i +1 < s.length()){
                String next = s.substring(i+1, i+2);
                if(next.equals("\\") || next.equals("\"")){
                    i++;
                } else{
                    i = i+3;
                }
            }
            count++;
        }
        if(i < s.length()){
            count++;
        }
        return count;
    }
}
