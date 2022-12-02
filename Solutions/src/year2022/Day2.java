package year2022;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Day2 {
    static String[] plays2 = {"A", "B", "C"};
    static String[] plays1 = {"X", "Y", "Z"};
    public static int play(int you, int elf){
        if(you == elf){return you + 3;}
        else if((elf - you == 1 && elf > you) || (elf < you && you - elf == 2)){return you;}
        else{return you + 6;}
    }
    public static int getPlay(int elf, int play){
        if(elf == 0){
            if(play == 0){return 2;}
            else if(play == 1){return 0;}
            else{return 1;}
        } else if(elf==1){
            if(play == 0){return 0;}
            else if(play == 1){return 1;}
            else{return 2;}
        } else{
            if(play == 0){return 1;}
            else if(play == 1){return 2;}
            else{return 0;}
        }
    }
    public static void main(String[] args) {
        try {
            File day1 = new File("Solutions\\data22\\day2input");
            BufferedReader br = new BufferedReader(new FileReader(day1));
            String line = br.readLine();
            int total = 0;
            while(line != null){
                String[] plays = line.split(" ");
                int you = -1;
                int elf = -1;
                for(int i =0; i<3; i++){
                    if(plays1[i].equals(plays[1])){you = i;}
                    if(plays2[i].equals(plays[0])){elf = i;}
                }
                you = getPlay(elf, you);
                total += play(you, elf) + 1;
                line = br.readLine();
            }
            System.out.println(total);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
