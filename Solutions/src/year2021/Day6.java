package year2021;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Day6 {
    static ArrayList<BigInteger> fish;
    public static void advanceOneDay(){
        ArrayList<BigInteger> newFish = new ArrayList<>();
        for(int i =0; i<9; i++){
            newFish.add(BigInteger.ZERO);
        }
        for(int i =0; i<8; i++){
            newFish.set(i, fish.get(i+1));
        }
        newFish.set(8, fish.get(0));
        newFish.set(6, newFish.get(6).add(fish.get(0)));
        fish = newFish;
    }
    public static void main(String[] args) {
        fish= new ArrayList<>();
        for(int i =0; i<9; i++){
            fish.add(BigInteger.ZERO);
        }
        try{
            Scanner s = new Scanner(new File("Solutions\\data21\\day6"));
            String[] input = s.nextLine().split(",");
            for(int i =0; i<input.length; i++){
                int num = Integer.parseInt(input[i]);
                fish.set(num, fish.get(num).add(BigInteger.ONE));
            }
            for(int i =0; i<256; i++){
                advanceOneDay();
            }
            BigInteger count = BigInteger.ZERO;
            for(int i =0; i<9; i++){
                count = count.add(fish.get(i));
            }
            System.out.println(count);


        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
