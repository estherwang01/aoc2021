package year2021;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class Day7 {
    public static int calculateFuel(HashMap<Integer, Integer> pos, int ind){
        int r = 0;
        for(Integer i: pos.keySet()){
            int cost = Math.abs(ind - i);
            int costp = (cost*(cost+1) )/ 2;
            r += costp * pos.get(i);
        }
        return r;
    }
    public static void main(String[] args) {
        try{
            Scanner s = new Scanner(new File("Solutions\\data21\\day7"));
            String[] input = s.nextLine().split(",");
            HashMap<Integer, Integer> pos = new HashMap<>();

            for(int i =0; i<input.length; i++){
                int num = Integer.parseInt(input[i]);
                pos.put(num, pos.getOrDefault(num, 0) + 1);
            }
            int min = Integer.MAX_VALUE;
            for(int i =0; i<pos.size(); i++){
                int cost = calculateFuel(pos, i);
                if(cost < min){
                    min = cost;
                }
            }
            System.out.println(min);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
