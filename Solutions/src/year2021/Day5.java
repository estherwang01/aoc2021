package year2021;


import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class Day5 {
    static HashMap<Tup, Integer> map;
    public static void main(String[] args) {
        try{
            Scanner s = new Scanner(new File("Solutions\\data21\\day5"));
            map = new HashMap<>();

            while(s.hasNextLine()){
                String line = s.nextLine();
                String[] stuff = line.split(" ");
                String[] start = stuff[0].split(",");
                String[] end = stuff[2].split(",");
                int sx = Integer.parseInt(start[0]);
                int sy = Integer.parseInt(start[1]);
                int ex = Integer.parseInt(end[0]);
                int ey = Integer.parseInt(end[1]);

                //pt 1
//                if(sx == ex || sy == ey) {
//                    updateMap(sx, sy, ex, ey);
//                }

                updateMap(sx, sy, ex, ey);
            }
            int count = 0;
            for(Tup t: map.keySet()){
                if(map.get(t) > 1){
                    count++;
                }
            }
            System.out.println(count);

        }catch(Exception e){

        }
    }

    public static Tup getNext(int x1, int y1, int x2, int y2){
        if(x1 == x2 && y1 == y2){
            return null;
        }
        if(x1 != x2){
            x1 += (x1 <= x2)? 1:-1;
        }
        if(y1 != y2){
            y1 += (y1 <= y2)? 1: -1;
        }
        return new Tup(x1, y1);
    }

    public static void updateMap(int startx, int starty, int endx, int endy){
        Tup first = new Tup(startx, starty);
        map.put(first, map.getOrDefault(first, 0) + 1);

        Tup t = getNext(startx, starty, endx, endy);
        while(t != null){
            map.put(t, map.getOrDefault(t, 0) + 1);
            t = getNext(t.x, t.y, endx, endy);
        }
    }
}
