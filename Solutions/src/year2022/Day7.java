package year2022;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Day7 {
    static int global_total = 0;
    static long smallest = Long.MAX_VALUE;
    static long needed = -1;
    static class Directory {
        Directory parent;
        HashSet<Directory> children;
        HashMap<String, Integer> files;
        String name;
        int size;
        public Directory(Directory p, String name){
            parent = p;
            children = new HashSet<>();
            this.name = name;
            size = 0;
            files = new HashMap<>();
        }
        @Override
        public boolean equals(Object other){
            return ((Directory) other).name.equals(name);
        }

        @Override
        public int hashCode(){
            return name.hashCode();
        }
    }
    public static int calc(Directory d){
        int sum = 0;
        for(String k: d.files.keySet()){
            sum += d.files.get(k);
        }
        return sum;
    }
    public static int calculate(Directory d){
        int total = calc(d);
        for(Directory child: d.children){
            int c = calculate(child);
            total += c;
        }
        if(total <= 100000) global_total += total;
        return total;
    }
    public static long calculate2(Directory d){
        long total = calc(d);
        for(Directory child: d.children){
            long c = calculate2(child);
            if(c >= needed && c < smallest){
                smallest = c;
            }
            total += c;
        }
        return total;
    }

    public static void main(String[] args) {
        try {
            File day1 = new File("Solutions\\data22\\day7input");
            BufferedReader br = new BufferedReader(new FileReader(day1));
            String line = br.readLine();

            Directory root = new Directory(null, "");
            Directory current = root;
            while(line != null){
                if(line.charAt(0) == '$'){
                    if(line.charAt(2) == 'c'){
                        String name = line.substring(5);
                        if(name.equals("..")){
                            current = current.parent;
                        }
                        else {
                            Directory n = new Directory(current, name);
                            if (!current.children.contains(n)) {
                                current.children.add(n);
                                current = n;
                            }
                        }
                        line = br.readLine();
                    }else{
                        //ls
                        line = br.readLine();
                        while(line != null && !line.startsWith("$")){
                            if(!line.startsWith("dir")){
                                String[] info = line.split(" ");
                                current.files.put(info[1], Integer.parseInt(info[0]));
                            }
                            line = br.readLine();
                        }
                    }
                }

            }
            int total = 70000000 - calculate(root);
            needed = 30000000 - total;
            calculate2(root);
            System.out.println(global_total); //part 1
            System.out.println(smallest); //part 2


        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
