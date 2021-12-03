package year2021;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day3 { // just kept part 2 because part 1 was trivial and also i'm lazy
//    static int num = 12;

    public static String getCommon(List<String> nums, int i, boolean most){
        int z = 0;
        int o = 0;
        for(String num: nums){
            String n = num.substring(i, i+1);
            if(n.equals("1")){
                o++;
            }
            else{
                z++;
            }
        }
        return ((o >= z && most) || (!most && o < z)) ? "1" : "0";
    }

    public static List<String> removeAllButCommon(List<String> nums, int i, boolean most){
        String common = getCommon(nums, i, most);
        ArrayList<String> keep = new ArrayList<>();

        for(String num: nums){
            if(num.substring(i, i+1).equals(common)){
                keep.add(num);
            }
        }
        return keep;
    }

    public static String get(List<String> nums, boolean most){
        List<String> current = nums;
        for(int i =0; i<nums.get(0).length() && current.size() > 1; i++){
            current = removeAllButCommon(current, i, most);
        }
        return current.get(0);
    }

    public static void main(String[] args) {
        try{
            Scanner s = new Scanner(new File("Solutions\\data21\\day3"));
            List<String> nums = new ArrayList<>();
            while(s.hasNextLine()){
                String next = s.nextLine();
                nums.add(next);
            }
            String ox = get(nums, true);
            String co2 = get(nums, false);

            int a = Integer.parseInt(ox, 2);
            int b = Integer.parseInt(co2, 2);
            System.out.println(a*b);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
