package year2015;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Day1 {
    public static void main(String[] args) {
        try{
            File day1 = new File("Solutions\\data15\\day1");
            BufferedReader br = new BufferedReader(new FileReader(day1));

            int count = 0;
            int next = br.read();
            boolean found = false;
            int basement = -1;
            int index = 0;

            while(next != -1){
                if(count == -1 && !found){
                    basement = index;
                    found = true;
                }
                if((char) next == ')'){
                    count--;
                }
                else{
                    count++;
                }
                next = br.read();
                index++;
            }
            System.out.println(count);
            System.out.println(basement);

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
