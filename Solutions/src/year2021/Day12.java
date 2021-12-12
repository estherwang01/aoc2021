package year2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day12 {
    public static boolean isCapital(String x){
        return (x.toUpperCase().equals(x));
    }

    public static int paths(HashMap<String, ArrayList<String>> map, boolean twice) {
        ArrayList<String> path = new ArrayList<>();
        path.add("start");
        return pathsUtil(path, map, twice);
    }

    public static int pathsUtil(List<String> path, HashMap<String, ArrayList<String>> map, boolean twice) {
        if (path.get(path.size() - 1).equals("end"))
            return 1;
        int sum = 0;
        for (String neighbor : map.get(path.get(path.size() - 1)))
            if (isCapital(neighbor) || (!isCapital(neighbor) && !path.contains(neighbor))) {
                path.add(neighbor);
                sum += pathsUtil(path, map, twice);
                path.remove(path.size() - 1);
            } else if (twice && !neighbor.equals("start") && !neighbor.equals("end")) {
                path.add(neighbor);
                sum += pathsUtil(path, map, false);
                path.remove(path.size() - 1);
            }
        return sum;
    }

    public static void main(String[] args) throws FileNotFoundException {

        HashMap<String, ArrayList<String>> map = new HashMap<>();
        String delimiter = System.getProperty("file.separator"); // must have code work on ALL computers
        Scanner s = new Scanner(new File("Solutions" + delimiter + "data21" + delimiter + "day12"));

        while(s.hasNextLine()) {
            String line = s.next();
            String[] se = line.split("-");
            String a = se[0];
            String b = se[1];
            ArrayList<String> adda = map.getOrDefault(a, new ArrayList<>());
            ArrayList<String> addb = map.getOrDefault(b, new ArrayList<>());
            adda.add(b);
            addb.add(a);
            map.put(a, adda);
            map.put(b, addb);
        }

        System.out.println(paths(map, false));
        System.out.println(paths(map, true));
    }

}
