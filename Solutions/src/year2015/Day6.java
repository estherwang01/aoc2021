package year2015;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Day6 {
    static int[][] grid = new int[1000][1000];

    public static void main(String[] args) {
        try{
            File day5 = new File("Solutions\\data15\\day6");
            BufferedReader br = new BufferedReader(new FileReader(day5));
            String next = br.readLine();
            while(next != null){
                String[] s = next.split(" ");
                if(s[0].equals("toggle")){
                    String[] start = s[1].split(",");
                    String[] end = s[3].split(",");
                    set(Integer.parseInt(start[0]), Integer.parseInt(start[1]), Integer.parseInt(end[0]), Integer.parseInt(end[1]), 3);
                }
                else{
                    int x = (s[1].equals("off")) ? 0 : 1;
                    String[] start = s[2].split(",");
                    String[] end = s[4].split(",");
                    set(Integer.parseInt(start[0]), Integer.parseInt(start[1]), Integer.parseInt(end[0]), Integer.parseInt(end[1]), x);
                }
                next = br.readLine();
            }
            int count = 0;
            for(int i =0; i<1000; i++){
                for(int j =0; j<1000; j++){
                    count += grid[i][j];
                }
            }
            System.out.println(count);
        }catch(Exception e){

        }
    }
    public static void set(int startRow, int startCol, int endRow, int endCol, int b){
        for(int i = startRow; i<=endRow; i++){
            for(int j = startCol; j<=endCol; j++){
                if(b == 0){
                    grid[i][j] = Math.max(0,grid[i][j]-1);
                }
                else if(b == 1){
                    grid[i][j] = grid[i][j]+1;
                }
                else{
                    grid[i][j] = grid[i][j]+2;
                }
            }
        }
    }

}
