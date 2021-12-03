package year2021;

import java.io.File;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) {
        try{
            Scanner s = new Scanner(new File("Solutions\\data21\\day2"));
            int x = 0;
            int y =0;
            int aim =0 ;
            while(s.hasNextLine()){
                String line = s.nextLine();
                String[] data = line.split(" ");
                int change = Integer.parseInt(data[1]);
                String dir = data[0];
                if(dir.equals("forward")){
                    x += change;
                    y += change * aim;
                }
                else if(dir.equals("down")){
                    aim += change;
                }
                else{
                    aim -= change;
                }
            }
            System.out.println(x*y);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
