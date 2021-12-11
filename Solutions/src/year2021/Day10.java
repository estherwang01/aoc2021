package year2021;

import java.io.File;
import java.util.*;

public class Day10 {
    static HashMap<String, Integer> cost;
    static HashMap<String, String> swap;

    public static int handleLine(String line){
        Stack<String> seen = new Stack<>();
        for(int i =0; i<line.length(); i++){
            String current = line.substring(i, i+1);
            if(current.equals("(") || current.equals("[") || current.equals("{") || current.equals("<")){
                seen.push(current);
            }
            else{
                if(seen.isEmpty()){
                    return cost.get(current);
                }
                String top = seen.pop();
                if(!swap.get(current).equals(top)){
                    return cost.get(current);
                }
            }
        }
        return 0;
    }
    public static long handleLine2(String line){
        Stack<String> seen = new Stack<>();
        for(int i =0; i<line.length(); i++){
            String current = line.substring(i, i+1);
            if(current.equals("(") || current.equals("[") || current.equals("{") || current.equals("<")){
                seen.push(current);
            }
            else{
                seen.pop();
            }
        }
        long t = 0;
        while(!seen.isEmpty()){
            t = (t* 5) + cost.get(swap.get(seen.pop()));
        }
        System.out.println(t);
        return t;
    }
    public static void main(String[] args) {
        cost = new HashMap<>();
        swap = new HashMap<>();
        cost.put(")", 1);
        cost.put("]", 2);
        cost.put("}", 3);
        cost.put(">", 4);
        swap.put(")", "(");
        swap.put("]", "[");
        swap.put(">", "<");
        swap.put("}", "{");
        swap.put("(", ")");
        swap.put("[", "]");
        swap.put("<", ">");
        swap.put("{", "}");
        try{
            String delimiter = System.getProperty("file.separator");
            Scanner s = new Scanner(new File("Solutions" + delimiter + "data21" + delimiter + "day10"));
            ArrayList<Long> total = new ArrayList<>();
            while(s.hasNextLine()){
                String line = s.nextLine();
                if(handleLine(line) == 0){
                    total.add(handleLine2(line));
                }
            }
            Collections.sort(total);
            long middle = total.get(total.size() / 2);
            System.out.println(middle);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
