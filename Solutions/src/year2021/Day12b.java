
package year2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

//p1 that used to work that i accidentally broke while writing p2
//time to completely rewrite everything :))
public class Day12b {
    public static HashSet<ArrayList<String>> all = new HashSet<>();
    public static boolean isCapital(String x){
        return (x.toUpperCase().equals(x));
    }
    public static int pathsUtil(HashMap<String, ArrayList<String>> map, String a, String b,
                                HashSet<String> visited, ArrayList<String> path, String x, boolean twice){
        if(a.equals(b)){
            for(int i =0; i<path.size()-1; i++){
                if(path.get(i).equals(path.get(i+1))){
                    return 0;
                }
            }
            if(!all.contains(path)){
                all.add(path);
                System.out.println(path);
            }
            return 1;
        }
        if(!isCapital(a) && !x.equals(a) || (x.equals(a) && twice)){
            visited.add(a);
        }
        if(!twice && x.equals(a)){
            twice = true;
        }
        int sum = 0;
        for(String neighbor: map.get(a)){
            if(!visited.contains(neighbor)){
                path.add(neighbor);
                sum += pathsUtil(map, neighbor, b, visited, path, x, twice);
                path.remove(neighbor);
                if(neighbor.equals(x)){
                    twice = false;
                }
            }
        }
        if(visited.contains(a)) {
            visited.remove(a);
        }
        return sum;
    }
    public static int paths(HashMap<String, ArrayList<String>> map){
        HashSet<String> visited = new HashSet<>();
        ArrayList<String> path = new ArrayList<>();
        int sum = 0;
        for(String key: map.keySet()){
            if(!isCapital(key) && !key.equals("start") && !key.equals("end")){
                sum += pathsUtil(map,"start", "end", visited, path, key, false);
            }
        }
        return sum;
    }
    public static void main(String[] args) throws FileNotFoundException {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        String delimiter = System.getProperty("file.separator");
        Scanner s = new Scanner(new File("Solutions" + delimiter + "data21" + delimiter + "day12"));

        while(s.hasNextLine()){
            String line = s.next();
            String[] se = line.split("-");
            String a = se[0];
            String b = se[1];
            ArrayList<String> adda = map.getOrDefault(a, new ArrayList<String>());
            ArrayList<String> addb = map.getOrDefault(b, new ArrayList<String>());
            adda.add(b);
            addb.add(a);
            map.put(a, adda);
            map.put(b, addb);
        }
        System.out.println(paths(map));
        System.out.println(all.size());
    }
}
