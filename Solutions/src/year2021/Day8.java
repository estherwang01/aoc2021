package year2021;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day8 {
    public static boolean permEquals(String s1, String s2){
        char[] ss1 = s1.toCharArray();
        char[] ss2 = s2.toCharArray();
        Arrays.sort(ss2);
        Arrays.sort(ss1);
        String sss1 = new String(ss1);
        String sss2 = new String(ss2);
        return sss1.equals(sss2);
    }
    public static int decode(String[] input, String[] output){
        String[] result = new String[10];
        String[] map = new String[7];

        ArrayList<String> six = new ArrayList<>();
        ArrayList<String> five = new ArrayList<>();

        for(int i =0; i<input.length; i++){
            if(input[i].length() == 2){
                result[1] = input[i];
            }
            else if(input[i].length() == 3){
                result[7] = input[i];
            }
            else if(input[i].length() == 4){
                result[4] = input[i];
            }
            else if(input[i].length() == 7){
                result[8] = input[i];
            }
            else if(input[i].length() == 6){
                six.add(input[i]);
            }
            else if(input[i].length() == 5){
                five.add(input[i]);
            }
        }

        String a = input[7];
        String b = input[1];
        String unique = "";
        for(int i =0; i<a.length(); i++){
            if(!b.contains(a.substring(i, i+1))){
                unique = a.substring(i, i+1);
            }
        }
        map[0] = unique;

        //6
        a = six.get(0);
        b = six.get(1);
        String c = six.get(2);

        unique = "X"; // what a and c share
        String unique2 = "X"; // what a and b share
        String unique3 = "X"; //what b and c share

        for(int i =0; i<a.length(); i++){
            String x = a.substring(i, i+1);
            if(b.contains(x) && !c.contains(x)){
                unique2 = x;
            }
            else if(!b.contains(x) && c.contains(x)){
                unique = x;
            }
        }
        for(int i =0; i<b.length(); i++){
            String x = b.substring(i, i+1);
            if(!a.contains(x) && c.contains(x)){
                unique3 = x;
            }
        }

        String cs = (result[1].contains(unique)) ? unique : (result[1].contains(unique2))? unique2 : unique3;
        map[2] = cs;
        if(!a.contains(cs)){
            result[6] = a;
            if(result[4].contains(unique2)){
                map[3] = unique3;
                map[5] = unique;
                result[9] = b;
                result[0] = c;
            }
            else{
                map[3] = unique3;
                map[5] = unique2;
                result[9] = c;
                result[0] = b;
            }
        }
        else if(!b.contains(cs)){
            result[6] = b;
            if(result[4].contains(unique2)){
                map[3] = unique;
                map[5] = unique3;
                result[9] = a;
                result[0] = c;
            }
            else{
                map[3] = unique;
                map[5] = unique2;
                result[9] = c;
                result[0] = a;
            }
        }
        else{
            result[6] = c;
            if(result[4].contains(unique)){
                map[3] = unique2;
                map[5] = unique3;
                result[9] = a;
                result[0] = b;
            }
            else{
                map[3] = unique2;
                map[5] = unique;
                result[9] = b;
                result[0] = a;
            }
        }

        a = five.get(0);
        b = five.get(1);
        String c1 = five.get(2);

        c = map[3];
        String e = map[5];

        String two = a.contains(c) && a.contains(e) ? a : b.contains(c) && b.contains(e) ? b : c1;
        String fi = !a.contains(c) && !a.contains(e) ? a : !b.contains(c) && !b.contains(e) ? b : c1;
        String thr = a.equals(two) && b.equals(fi) || a.equals(fi) && b.equals(two) ? c1 :
                b.equals(two) && c1.equals(fi) || b.equals(fi) && c1.equals(two) ? a : b;

        result[2] = two;
        result[5] = fi;
        result[3] = thr;

        String current = "";

        for(int i =0; i<output.length; i++){
            String x = output[i];
            for(int j =0; j<result.length; j++){
                if(permEquals(result[j], x)){
                    current += j;
                }
            }
        }
        return Integer.parseInt(current);
    }
    public static void main(String[] args) {
        try{
            Scanner s = new Scanner(new File("Solutions\\data21\\day8"));
            int count = 0;
            while(s.hasNextLine()){
                String next = s.nextLine();
                String output = next.substring(next.indexOf("|") + 1);
                String input = next.substring(0, next.indexOf("|"));
                String[] in = input.split(" ");
                String[] out = output.split(" ");
                int n = decode(in, out);
                count += n;
            }
            System.out.println(count);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
