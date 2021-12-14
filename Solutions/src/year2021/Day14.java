package year2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

//this is those goddamn lanternfish all over again
public class Day14 {
    static ArrayList<Tuple> letters;

    public static long countFrequency(){
        HashMap<String, Long> count = new HashMap<>();
        for(Tuple L: letters){
            count.put(L.x, count.getOrDefault(L.x, (long) 0) + 1);
        }
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        for(String k: count.keySet()){
            long c = count.get(k);
            if(c > max){
                max = c;
            }
            if(c < min){
                min = c;
            }
        }
        return max - min;
    }
    public static void insert(String rule){
        String a = rule.substring(0,1);
        String b = rule.substring(1,2);
        String ins = rule.substring(6);

        for(int i =0; i<letters.size()-1; i++){
            if(!letters.get(i).thisRound && !letters.get(i+1).thisRound){
                if(letters.get(i).x.equals(a) && letters.get(i+1).x.equals(b)){
                    Tuple t = new Tuple(ins, true);
                    letters.add(i+1, t);
                }
            }
        }
    }

    public static void nextTimeStep(){
        for(int i =0; i<letters.size(); i++){
            if(letters.get(i).thisRound){
                letters.set(i, new Tuple(letters.get(i).x, false));
            }
        }
    }

    public static void stepOnce(ArrayList<String> instructions){
        for(String i: instructions){
            insert(i);
        }
        nextTimeStep();
    }
    public static void main(String[] args) throws FileNotFoundException {
        String delimiter = System.getProperty("file.separator");
        Scanner s = new Scanner(new File("Solutions" + delimiter + "data21" + delimiter + "day14"));
        letters = new ArrayList<>();
        String input = s.nextLine();
        s.nextLine();
        ArrayList<String> instructions = new ArrayList<>();
        while(s.hasNextLine()){
            instructions.add(s.nextLine());
        }

        for(int i =0; i<input.length(); i++){
            Tuple t = new Tuple(input.substring(i, i+1), false);
            letters.add(t);
        }

        for(int i =0; i<40; i++){
            stepOnce(instructions);
//            System.out.println(letters.size());
        }
        System.out.println(countFrequency());

    }
}

class Tuple{
    String x;
    boolean thisRound;
    public Tuple(String a, boolean b){
        x = a;
        thisRound = b;
    }
    @Override
    public boolean equals(Object o){
        Tuple other = (Tuple) o;
        return this.x == other.x && this.thisRound == other.thisRound;
    }
    @Override
    public int hashCode(){
        return x.hashCode() << 16 + (thisRound ? 1: 0);
    }
}
