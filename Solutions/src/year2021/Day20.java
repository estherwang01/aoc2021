package year2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day20 {
    static String enhance;
    static ArrayList<ArrayList<Integer>> image;
    static boolean lit = false;

    public static int getEnhanced(int[] col1, int[] col2, int[] col3){
        String bin = col1[0] + "" + col2[0] + "" + col3[0] + "" +
                col1[1] + "" + col2[1] + "" + col3[1] + "" +
                col1[2] + "" + col2[2] + "" + col3[2];

        int i = Integer.parseInt(bin, 2);
        String c = enhance.substring(i, i+1);
        return c.equals("#") ? 1: 0;
    }

    public static void enhance(){
        pad();
        ArrayList<ArrayList<Integer>> image2 = new ArrayList<>();
        for(int i = 0; i<image.size(); i++){
            ArrayList<Integer> row = new ArrayList<>();
            for(int j =0; j<image.get(0).size(); j++){
                row.add(0);
            }
            image2.add(row);
        }

        for(int i =0; i<image.size(); i++){
            for(int j = 0; j<image.get(i).size(); j++){
                int[] col2 = new int[]{i-1>=0 ? image.get(i-1).get(j) : lit ? 1 : 0,
                image.get(i).get(j), i+1 < image.size() ? image.get(i+1).get(j) : lit ? 1 : 0};
                int[] col1 = j - 1 < 0 ? new int[]{lit ? 1: 0, lit? 1: 0, lit? 1:0}:
                        new int[]{i-1>=0 ? image.get(i-1).get(j-1) : lit ? 1 : 0,
                                image.get(i).get(j-1), i+1 < image.size() ? image.get(i+1).get(j-1) : lit ? 1 : 0};
                int[] col3 = j + 1 >= image.get(0).size() ? new int[]{lit ? 1: 0, lit? 1: 0, lit? 1:0}:
                        new int[]{i-1>=0 ? image.get(i-1).get(j+1) : lit ? 1 : 0,
                                image.get(i).get(j+1), i+1 < image.size() ? image.get(i+1).get(j+1) : lit ? 1 : 0};

                image2.get(i).set(j, getEnhanced(col1, col2, col3));
            }
        }
        image = image2;
        lit = !lit;
    }

    public static void pad(){
        int padding = lit? 1: 0;
        for(int i =0; i<image.size(); i++){
            image.get(i).add(0, padding);
            image.get(i).add(padding);
        }
        int len = image.get(0).size();
        ArrayList<Integer> start = new ArrayList<>();
        for(int i =0; i<len; i++){
            start.add(padding);
        }
        image.add(0, start);
        image.add(start);
    }

    public static void main(String[] args) throws FileNotFoundException {
        String delimiter = System.getProperty("file.separator");
        Scanner s = new Scanner(new File("Solutions" + delimiter + "data21" + delimiter + "day20"));
        enhance = s.nextLine();
        s.nextLine();

        image = new ArrayList<>();

        while(s.hasNextLine()){
            String line = s.nextLine();
            ArrayList<Integer> row = new ArrayList<>();
            for(int i =0; i<line.length(); i++){
                String c = line.substring(i, i+1);
                if(c.equals(".")){
                    row.add(0);
                }
                else{
                    row.add(1);
                }
            }
            image.add(row);
        }

        for(int i =0; i<50; i++){
            enhance();
        }

        int count = 0;
        for(int i =0; i<image.size(); i++){
            for(int j =0; j<image.size(); j++){
                if(image.get(i).get(j) == 1){
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
