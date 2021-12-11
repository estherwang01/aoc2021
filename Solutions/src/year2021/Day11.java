package year2021;

import java.io.File;
import java.util.Scanner;

public class Day11 {
    static int flashes = 0;
    public static int[][] flash(int[][] oct, int i, int j){
        flashes++;
        oct[i][j] = Integer.MIN_VALUE;
        int[] dx = {1, -1, 0, 0, 1, -1, 1, -1};
        int[] dy = {0, 0, 1, -1, 1, -1, -1, 1};
        for(int x =0; x<8; x++) {
            int a = i + dx[x];
            int b = j + dy[x];
            if(a < 0 || a >= 10 || b < 0 || b >= 10){
                continue;
            }
            oct[a][b] += 1;
            if (oct[a][b] > 9) {
                flash(oct, a, b);
            }
        }
        return oct;
    }
    public static int[][] step1(int[][] oct){
        flashes = 0;
        for(int i =0; i<10; i++){
            for(int j =0; j<10; j++){
                oct[i][j] += 1;
            }
        }
        for(int i =0; i<10; i++){
            for(int j = 0;j <10; j++){
                if(oct[i][j] > 9){
                    oct = flash(oct, i, j);
                }
            }
        }
        for(int i =0; i<10; i++){
            for(int j =0; j<10; j++){
                if(oct[i][j] < 0){
                    oct[i][j] = 0;
                }
            }
        }
        return oct;
    }

    public static void main(String[] args) {
        try{
            //are u happy now charles?
            String delimiter = System.getProperty("file.separator");
            Scanner s = new Scanner(new File("Solutions" + delimiter + "data21" + delimiter + "day11"));
            int[][] octopi = new int[10][10];
            int j = 0;
            while(s.hasNextLine()){
                String line = s.nextLine();
                int[] row = new int[10];
                for(int i =0; i<line.length(); i++){
                    row[i] = Integer.parseInt(line.substring(i, i+1));
                }
                octopi[j] = row;
                j++;
            }
            int x = 1;
            octopi = step1(octopi);
            while(flashes != 100){
                octopi = step1(octopi);
                x++;
            }
            //part 1
//            for(int i =0; i<100; i++){
//                octopi = step1(octopi);
//            }

            System.out.println(x);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
