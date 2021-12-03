package year2015;

import java.io.File;
import java.util.*;

public class Day9 {
    private static HashMap<String, HashMap<String, Integer>> map;

    private static int getDistance(String[] route){
        int count = 0;
        for(int i =0; i< route.length-1; i++){
            String current = route[i];
            String next = route[i+1];
            HashMap<String, Integer> neighbors = map.get(current);
            if(neighbors.containsKey(next)){
                count += neighbors.get(next);
            }else{
                count += Integer.MIN_VALUE;
            }
        }
        return count;
    }
    public static <T> void swap(T[] array, int first, int second) {
        T temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    private static <T> void allPermutationsHelper(T[] permutation, List<T[]> permutations, int n) {
        // Base case - we found a new permutation, add it and return
        if (n <= 0) {
            permutations.add(permutation);
            return;
        }
        // Recursive case - find more permutations by doing swaps
        T[] tempPermutation = Arrays.copyOf(permutation, permutation.length);
        for (int i = 0; i < n; i++) {
            swap(tempPermutation, i, n - 1); // move element at i to end
            // move everything else around, holding the end constant
            allPermutationsHelper(tempPermutation, permutations, n - 1);
            swap(tempPermutation, i, n - 1); // backtrack
        }
    }

    private static <T> List<T[]> permutations(T[] original) {
        List<T[]> permutations = new ArrayList<>();
        allPermutationsHelper(original, permutations, original.length);
        return permutations;
    }

    public static void main(String[] args) {
        map = new HashMap<>();
        try{
            Scanner s = new Scanner(new File("Solutions\\data15\\day9"));
            String next;
            HashSet<String> all = new HashSet<>();

            while(s.hasNextLine()){
                next = s.nextLine();
                String[] info = next.split(" ");
                String from = info[0];
                String to = info[2];
                Integer distance = Integer.parseInt(info[4]);

                HashMap<String, Integer> distances;
                if(map.containsKey(from)){
                    distances = map.get(from);
                }
                else{
                    distances = new HashMap<>();
                }
                distances.put(to, distance);
                map.put(from, distances);

                if(map.containsKey(to)){
                    distances = map.get(to);
                }
                else{
                    distances = new HashMap<>();
                }
                distances.put(from, distance);
                map.put(to, distances);
                all.add(from);
                all.add(to);
            }
           String[] original = new String[all.size()];
            int x = 0;
            for(String l: all){
                original[x] = l;
                x++;
            }

            List<String[]> perms = permutations(original);
            int max = Integer.MIN_VALUE;
            for(String[] p: perms){
//                min = Math.min(min, getDistance(p));
                max = Math.max(max, getDistance(p));
            }
            System.out.println(max);

        }catch(Exception e){
            e.printStackTrace();
        }

    }

}

