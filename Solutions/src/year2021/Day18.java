package year2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day18 {
    static ArrayList<ArrayList<Num>> hw = new ArrayList<>();

    public static void parseInput(String filename) throws FileNotFoundException {
        Scanner s = new Scanner(new File(filename));
        while(s.hasNextLine()){
            hw.add(parseNumber(s.nextLine()));
        }
    }
    public static ArrayList<Num> parseNumber(String line){
        int parens = 0;
        ArrayList<Num> num = new ArrayList<>();
        for(int i =0; i<line.length(); i++){
            String token = line.substring(i, i+1);
            if(token.equals("[")){
                parens++;
            }
            else if(token.equals("]")){
                parens--;
            }
            else if(token.equals(",")){
                continue;
            }
            else{
                num.add(new Num(Integer.parseInt(token), parens));
            }
        }
        return num;
    }

    public static int magnitude(ArrayList<Num> n){
        while(n.size() > 1){
            for(int i =0; i<n.size()-1; i++){
                Num a = n.get(i);
                Num b = n.get(i+1);
                if(a.depth == b.depth){
                    n.remove(i+1);
                    n.set(i, new Num(3*a.value + 2*b.value, a.depth-1));
                    i = Math.max(-1, i-3);
                }
            }
        }
        return n.get(0).value;
    }
    public static int getMax2Sum(){
        int max = Integer.MIN_VALUE;
        for(int i =0; i<hw.size(); i++){
            for(int j = 0; j < hw.size(); j++) {
                if(j == i){
                    continue;
                }
                max = Math.max(max, magnitude(reduce(addSnailNumbers(deepCopy(hw.get(i)), deepCopy(hw.get(j))))));
            }
        }
        return max;
    }
    public static int doHw(){
        ArrayList<Num> sum = doSummation();
        return magnitude(sum);
    }
    public static ArrayList<Num> doSummation(){
        ArrayList<Num> sum = deepCopy(hw.get(0));
        for(int i =1; i<hw.size(); i++){
            ArrayList<Num> next = deepCopy(hw.get(i));
            sum = reduce(addSnailNumbers(sum, next));
        }
        return sum;
    }
    public static ArrayList<Num> addSnailNumbers(ArrayList<Num> n1, ArrayList<Num> n2){
        ArrayList<Num> added = new ArrayList<>();
        for(Num n: n1){
            added.add(new Num(n.value, n.depth + 1));
        }
        for(Num n: n2){
            added.add(new Num(n.value, n.depth+1));
        }
        return added;
    }

    public static ArrayList<Num> reduce(ArrayList<Num> n){
        ArrayList<Num> reduced = reduceHelper(n);
        while(reduced != null){
            reduced = reduceHelper(reduced);
        }
        return n;
    }
    public static ArrayList<Num> reduceHelper(ArrayList<Num> n){
        for(int i =0; i<n.size(); i++){ //explode
            if(n.get(i).depth > 4){
                if(i-1 >= 0){
                    n.set(i-1, new Num(n.get(i-1).value + n.get(i).value, n.get(i-1).depth));
                }
                if(i+2 < n.size()){
                    n.set(i+2, new Num(n.get(i+2).value + n.get(i+1).value, n.get(i+2).depth));
                }
                n.remove(i);
                n.set(i, new Num(0, n.get(i).depth - 1));
                return n;
            }
        }
        for(int i =0; i<n.size(); i++){ //split
            Num val = n.get(i);
            if(val.value >=10){
                int left = (int) Math.floor(val.value/2.0);
                int right = (int) Math.ceil(val.value/2.0);
                n.set(i, new Num(left, val.depth + 1));
                n.add(i+1, new Num(right, val.depth + 1));
                return n;
            }
        }
        return null; //no reduction applies
    }
    public static void main(String[] args) throws FileNotFoundException {
        String delimiter = System.getProperty("file.separator");
        String filename = "Solutions" + delimiter + "data21" + delimiter + "day18";
        parseInput(filename);
        System.out.println(doHw());
        System.out.println(getMax2Sum());

    }
    public static ArrayList<Num> deepCopy(ArrayList<Num> n){
        ArrayList<Num> na  =new ArrayList<>();
        for(Num x: n){
            na.add(new Num(x.value, x.depth));
        }
        return na;
    }
    static class Num{
        int value;
        int depth;
        public Num(int v, int d){
            value = v;
            depth = d;
        }
        @Override
        public String toString(){
            return value + ": " + depth;
        }
    }
}
