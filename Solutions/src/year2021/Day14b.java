package year2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

//oh when oh when will i ever learn to optimize the first time through
public class Day14b {
    public static HashMap<String, Long> step(HashMap<String, String> rules, HashMap<String, Long> adj){
        HashMap<String, Long> newm = new HashMap<>();

        for(String key: adj.keySet()){
            Long v = adj.get(key);
            if(rules.containsKey(key)){
                String rule = rules.get(key);
                String start = key.substring(0,1);
                String end = key.substring(1,2);
                String a = start + rule;
                String b = rule + end;
                newm.put(a, newm.getOrDefault(a, (long) 0) + v);
                newm.put(b, newm.getOrDefault(b, (long) 0) + v);
            }
            else{
                newm.put(key, newm.getOrDefault(key, (long) 0)+ v);
            }
        }
        return newm;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String delimiter = System.getProperty("file.separator");
        Scanner s = new Scanner(new File("Solutions" + delimiter + "data21" + delimiter + "day14"));
        String input = s.nextLine();
        s.nextLine();
        HashMap<String, String> map = new HashMap<>();
        while(s.hasNextLine()){
            String line = s.nextLine();
            String[] stuff = line.split(" ");
            map.put(stuff[0], stuff[2]);
        }

        HashMap<String, Long> count = new HashMap<>();
        for(int i =0; i<input.length()-1; i++){
            String x = input.substring(i, i+2);
            count.put(x, count.getOrDefault(x, (long)0) +1);
        }

        for(int i =0; i<40; i++){
            count = step(map, count);
        }

        HashMap<String, Long> f = new HashMap<>();
        for(String k: count.keySet()){
            String a = k.substring(0, 1);
            String b = k.substring(1,2);
            Long v = count.get(k);
            f.put(a, f.getOrDefault(a, (long) 0)+v);
            f.put(b, f.getOrDefault(b, (long)0)+v);
        }

        f.put(input.substring(0,1), f.getOrDefault(input.substring(0,1), (long)0)+1);
        f.put(input.substring(input.length()-1), f.getOrDefault(input.substring(input.length()-1), (long)0)+1);

        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        for(String x: f.keySet()){
            long v = f.get(x);
            if(v > max){
                max = v;
            }
            if(v < min){
                min = v;
            }
        }
        System.out.println((max-min)/2);

    }
}
