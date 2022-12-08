package year2022;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Day8 {
    static int[][] directions = {{0, 1}, {0, -1}, {-1, 0}, {1,0}};
    public static void main(String[] args) {
        try {

            ArrayList<ArrayList<Integer>> grid = new ArrayList<ArrayList<Integer>>();
            File day1 = new File("Solutions\\data22\\day8input");
            BufferedReader br = new BufferedReader(new FileReader(day1));
            String line = br.readLine();

            while(line != null){
                char[] l = line.toCharArray();
                ArrayList<Integer> nums = new ArrayList<>();
                for(char c: l){nums.add(Integer.parseInt(c + ""));}
                grid.add(nums);
                line = br.readLine();
            }
            int total = 0;
            for(int i =0; i<grid.size(); i++){
                for(int j =0; j<grid.get(i).size(); j++){
                    boolean visible = false;
                    for(int[] dir: directions){
                        int curx = i;
                        int cury = j;
                        boolean blocked = false;
                        while(true){
                            curx += dir[0];
                            cury += dir[1];
                            if(curx >= grid.size() || cury >= grid.get(i).size() || curx < 0 || cury < 0) break;
                            if(grid.get(curx).get(cury) >= grid.get(i).get(j)) blocked = true; }
                        if(!blocked) visible = !blocked; }
                    if(visible) total++;
                }
            }

            int total2 = 0;
            for(int i =0; i<grid.size(); i++){
                for(int j =0; j<grid.get(i).size(); j++){
                   int prod = 1;
                    for(int[] dir: directions){
                        int curx = i;
                        int cury = j ;
                        int delta = 0;
                        while(true){
                            curx += dir[0];
                            cury += dir[1];
                            delta++;
                            if(curx >= grid.size() || cury >= grid.get(i).size() || curx < 0 || cury < 0){
                                delta -=1;
                                break; }
                            if(grid.get(curx).get(cury) >= grid.get(i).get(j)) break;
                        }
                        prod *= delta;
                    }
                    total2 = Math.max(total2, prod);
                }
            }
            System.out.println(total); //p1
            System.out.println(total2); //p2


        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
