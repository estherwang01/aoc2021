package year2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day25 {
    static ArrayList<ArrayList<Integer>> c; //1 = down, 2 = right, 0 = empty

    public static boolean step(){
        boolean moved = false;

        ArrayList<ArrayList<Integer>> stepped = new ArrayList<>();
        for(int i =0; i<c.size(); i++){
            ArrayList<Integer> current = new ArrayList<>();
            for(int j =0; j<c.get(i).size(); j++){
                current.add(c.get(i).get(j));
            }
            stepped.add(current);
        }

        //step right
        for(int i =0; i<c.size(); i++){
            for(int j =0; j<c.get(i).size(); j++){
                if (c.get(i).get(j) == 2){
                    int next = j + 1 < c.get(i).size()? j + 1 : 0;
                    if(c.get(i).get(next) == 0){
                        moved = true;
                        stepped.get(i).set(next, 2);
                        stepped.get(i).set(j, 0);
                    }
                }

            }
        }

        c = stepped;

        ArrayList<ArrayList<Integer>> next = new ArrayList<>();
        for(int i =0; i<c.size(); i++){
            ArrayList<Integer> current = new ArrayList<>();
            for(int j =0; j<c.get(i).size(); j++){
                current.add(c.get(i).get(j));
            }
            next.add(current);
        }

        //step down
        for(int i =0; i<c.size(); i++){
            for(int j =0; j<c.get(i).size(); j++){
                if(c.get(i).get(j) == 1){
                    int n = i + 1 < c.size() ? i + 1 : 0;
                    if(c.get(n).get(j) == 0){
                        moved = true;
                        next.get(n).set(j, 1);
                        next.get(i).set(j, 0);
                    }
                }
            }
        }
        c = next;
        return moved;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String delimiter = System.getProperty("file.separator");
        Scanner s = new Scanner(new File("Solutions" + delimiter + "data21" + delimiter + "day25"));

        c = new ArrayList<>();

        while(s.hasNextLine()){
            String line = s.nextLine();
            ArrayList<Integer> next = new ArrayList<>();
            for(int i =0; i<line.length(); i++){
                String t = line.substring(i, i+1);
                if(t.equals(">")){
                    next.add(2);
                }
                else if(t.equals("v")){
                    next.add(1);
                }
                else{
                    next.add(0);
                }
            }
            c.add(next);
        }

        int count = 0;
        while(step()){
            count++;
        }
        System.out.println(count+1);
    }
}
