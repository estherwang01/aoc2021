package year2022;

import year2021.Tup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;

public class Day9 {

    public static Tup updateTail(Tup head, Tup tail){
        if(Math.abs(head.x - tail.x) <= 1 && Math.abs(head.y - tail.y) <= 1) return tail;
        int updatex = (head.x > tail.x) ? 1 :  (head.x < tail.x) ? -1: 0;
        int updatey = (head.y > tail.y) ? 1: (head.y < tail.y) ? -1: 0;
        Tup newTail = new Tup(tail.x, tail.y);
        if(updatex != 0) newTail.x += updatex;
        if(updatey != 0) newTail.y += updatey;
        return newTail;
    }
    public static Tup updatehead(Tup head, String dir){
        if(dir.equals("R")) return new Tup(head.x+1, head.y);
        else if(dir.equals("L")) return new Tup(head.x-1, head.y);
        else if(dir.equals("U")) return new Tup(head.x, head.y+1);
        else return new Tup(head.x, head.y-1);
    }

    public static void main(String[] args) {
        try {
            File day1 = new File("Solutions\\data22\\day9input");
            BufferedReader br = new BufferedReader(new FileReader(day1));
            String line = br.readLine();
            HashSet<Tup> seen = new HashSet<>();

            int n = 9; // change to 1 for part 1
            Tup head = new Tup(0,0);
            Tup[] tails = new Tup[n];
            for(int i =0; i<n; i++) tails[i] = new Tup(0, 0);
            seen.add(tails[0]);

            while(line != null){
                String[] info = line.split(" ");
                int num = Integer.parseInt(info[1]);
                for(int i =0; i<num; i++){
                    head = updatehead(head, info[0]);
                    Tup cur = head;
                    for(int j = 0; j<tails.length; j++){
                        Tup next = tails[j];
                        tails[j] = updateTail(cur, next);
                        cur = tails[j];
                    }
                    seen.add(tails[n-1]);
                }
                line = br.readLine();
            }
            System.out.println(seen.size());

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
