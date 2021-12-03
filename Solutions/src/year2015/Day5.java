package year2015;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Day5 {

    public static void main(String[] args) {
        try{
            File day5 = new File("Solutions\\data15\\day5");
            BufferedReader br = new BufferedReader(new FileReader(day5));

            String next = br.readLine();
            int count = 0;
            while(next != null){
                if(isNice2(next)){
                    count++;
                }
                next = br.readLine();
            }
            System.out.println(count);

        }catch(Exception e){

        }
    }
    public static boolean isNice2(String word){
        HashMap<String, Integer> doubles = new HashMap<>();
        boolean found1 = false;
        boolean found2 = false;
        for(int i =0; i<word.length()-1 && (!found1 || !found2); i++){
            String d = word.substring(i, i+2);
            if(!doubles.containsKey(d)){
                doubles.put(d, i);
            }
            else{
                if(i - doubles.get(d) >= 2){
                    found1 = true;
                }
            }
            if(i +2 < word.length()){
                if(word.substring(i, i+1).equals(word.substring(i+2, i+3))){
                    found2 = true;
                }
            }
        }
        return found1 && found2;
    }
    public static boolean isNice(String word){
        ArrayList<String> forbidden = new ArrayList();
        forbidden.add("ab");
        forbidden.add("cd");
        forbidden.add("pq");
        forbidden.add("xy");

        ArrayList<String> vowels = new ArrayList<>();
        vowels.add("a");
        vowels.add("e");
        vowels.add("i");
        vowels.add("o");
        vowels.add("u");

        boolean d = false;
        int vowel = 0;

        for(int i =0; i<word.length() - 1; i++){
            if(forbidden.contains(word.substring(i, i+2))){
                return false;
            }
            if(vowels.contains(word.substring(i, i+1))){
                vowel++;
            }
            if(word.substring(i, i+1).equals(word.substring(i+1, i+2))){
                d = true;
            }
        }
        if(vowels.contains(word.substring(word.length()-1))){
            vowel++;
        }
        return vowel >= 3 && d;
    }
}
