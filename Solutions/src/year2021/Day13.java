package year2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Day13 {
    static HashSet<Tup> dots;
    public static void visualize(){
        int maxX = 0;
        int maxY = 0;
        for(Tup dot: dots){
            if(dot.x > maxX){
                maxX = dot.x;
            }
            if(dot.y > maxY){
                maxY = dot.y;
            }
        }

        for(int i =0; i<=maxY; i++){
            for(int j =0; j<=maxX; j++){
                if(dots.contains(new Tup(j,i))){
                    System.out.print("#");
                }
                else{
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
    public static void foldLeft(int x){
        HashSet<Tup> newDots = new HashSet<>();
        for(Tup dot: dots){
            if(dot.x > x){
                int diff = dot.x - x;
                int flipped = x - diff;
                newDots.add(new Tup(flipped, dot.y));
            }
            else{
                newDots.add(dot);
            }
        }
        dots = newDots;
    }
    public static void foldUp(int y){
        HashSet<Tup> newDots = new HashSet<>();
        for(Tup dot: dots){
            if(dot.y > y){
                int diff = dot.y - y;
                int flipped = y - diff;
                newDots.add(new Tup(dot.x, flipped));
            }
            else{
                newDots.add(dot);
            }
        }
        dots = newDots;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String delimiter = System.getProperty("file.separator"); // must have code work on ALL computers
        Scanner s = new Scanner(new File("Solutions" + delimiter + "data21" + delimiter + "day13"));
        dots = new HashSet<>();
        boolean d = true;
        ArrayList<String> folding = new ArrayList<>();
        while(s.hasNextLine()){
            String line = s.nextLine();
            if(!d){
                folding.add(line.split(" ")[2]);
            }
            else {
                if (line.equals("")) {
                    d = false;
                }else{
                    Tup dot = new Tup(Integer.parseInt(line.split(",")[0]),
                            Integer.parseInt(line.split(",")[1]));
                    dots.add(dot);
                }
            }

        }
        for(int i =0; i<folding.size(); i++){
            String[] instructions = folding.get(i).split("=");
            if(instructions[0].equals("x")){
                foldLeft(Integer.parseInt(instructions[1]));
            }
            else{
                foldUp(Integer.parseInt(instructions[1]));
            }
            if(i == 0){
                //first fold
                System.out.println(dots.size());
            }
        }
        visualize();
    }
}
