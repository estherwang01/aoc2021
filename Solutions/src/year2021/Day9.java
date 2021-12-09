package year2021;

import java.io.File;
import java.util.*;

public class Day9 {
    public static int getBasin(int i, int j, ArrayList<ArrayList<Integer>>map){
        if(map.get(i).get(j) == 9){
            return 0;
        }
        map.get(i).set(j, 9);
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int count = 1;
        for(int x = 0; x< 4; x++){
            int ddx = i + dx[x];
            int ddy = j + dy[x];
            if(ddx >= 0 && ddx < map.size() && ddy >= 0 && ddy < map.get(0).size()){
                count += getBasin(ddx, ddy, map);
            }
        }
        return count;
    }
    public static void main(String[] args) {
        try{
            Scanner s = new Scanner(new File("Solutions\\data21\\day9"));
            ArrayList<ArrayList<Integer>> map = new ArrayList<>();
            while(s.hasNextLine()){
                ArrayList<Integer> current = new ArrayList<>();
                String next = s.nextLine();
                for(int i =0; i<next.length(); i++){
                    int num = Integer.parseInt(next.substring(i, i+1));
                    current.add(num);
                }
                map.add(current);
            }

            //Part 2
            ArrayList<Integer> basins = new ArrayList<>();
            for(int i =0; i<map.size(); i++){
                for(int j =0; j<map.get(i).size(); j++){
                    if(map.get(i).get(j) != 9) {
                        basins.add(getBasin(i, j, map));
                    }
                }
            }
            Collections.sort(basins);
            int r =  basins.get(basins.size()-1) * basins.get(basins.size()-2) * basins.get(basins.size()-3);
            System.out.println(r);

            //part 1 (comment out part 2 for this to work)
            int sum = 0;
            for(int i =0; i<map.size(); i++){
                for(int j = 0; j<map.get(i).size(); j++){

                    boolean right = j + 1 < map.get(i).size() ? map.get(i).get(j) < map.get(i).get(j+1) : true;
                    boolean left = j - 1 >= 0 ? map.get(i).get(j) < map.get(i).get(j-1) : true;
                    boolean up = i - 1 >= 0 ? map.get(i).get(j) < map.get(i-1).get(j) : true;
                    boolean down = i + 1 < map.size() ? map.get(i).get(j) < map.get(i+1).get(j) : true;

                    if(right && left && up && down){
                        System.out.println(map.get(i).get(j));
                        sum += map.get(i).get(j) + 1;
                    }
                }
            }
            System.out.println(sum);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
