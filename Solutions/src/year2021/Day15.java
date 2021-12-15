package year2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import util.*;

public class Day15 {

    public static int[] row5(int[] row){
        int[] n = new int[row.length * 5];
        int index = 0;
        for(int x =0; x<5; x++) {
            for (int i = 0; i < row.length; i++) {
                n[index] = row[i] + x;
                index++;
            }
        }
        return n;
    }
    public static int[] incRow(int[] row, int x){
        int[] n = new int[row.length];
        for(int i =0; i<row.length; i++){
            n[i] = row[i] + x <= 9 ? row[i] + x : (row[i] + x) % 10 + 1;
        }
        return n;
    }

    public static int[][] times5(int[][] grid){
        int[][] n = new int[grid.length][grid[0].length * 5];
        for(int i =0; i<grid.length; i++){
            int[] row = grid[i];
            n[i] = row5(row);
        }
        int[][] n2 = new int[grid.length * 5][grid[0].length * 5];
        int index = 0;
        for(int i =0; i < 5; i++){
            for(int x = 0; x < n.length; x++){
                n2[index] = incRow(n[x], i);
                index++;
            }
        }
        return n2;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String delimiter = System.getProperty("file.separator");
        Scanner s = new Scanner(new File("Solutions" + delimiter + "data21" + delimiter + "day15"));
        ArrayList<ArrayList<Integer>> grid = new ArrayList<>();

        while(s.hasNextLine()){
            String next = s.nextLine();
            ArrayList<Integer> current = new ArrayList<>();
            for(int i =0; i<next.length(); i++){
                int n = Integer.parseInt(next.substring(i,i+1));
                current.add(n);
            }
            grid.add(current);
        }

        int[][] g = new int[grid.size()][grid.get(0).size()];
        for(int i =0; i<grid.size(); i++){
            for(int j =0; j<grid.get(i).size(); j++){
                g[i][j] = grid.get(i).get(j);
            }
        }

        int[][] n = times5(g);
        System.out.println(GraphAlgos.dijkstra(g)); // part 1
        System.out.println(GraphAlgos.dijkstra(n)); // part 2
    }
}
