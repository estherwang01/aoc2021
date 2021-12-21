package year2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
//ejml?

public class Day19 {
    //generate all 24 orientations given an x,y,z coordinate
    static int[][] allOrientations(int x, int y, int z){
        int[][] all = new int[24][3];
        int index = 0;
        //for each possible orientation, get all the rotations of it
        for(int[] or: orientations(x,y,z)){
            int[][] rot =rotations(or[0], or[1], or[2]);
            for(int[] orRot: rot){
                all[index] = orRot;
                index++;
            }
        }
        return all;
    }
    static int[][] orientations(int x, int y, int z){ //all the different z orientations
        //z facing z, x, y, -z, -y, -x in that order
        return new int[][]{{x,y,z},{z,y,-x}, {x,z,-y}, {x,-y,-z},
                {x,-z,y}, {-z,y,x}};
    }
    static int[][] rotations(int x, int y, int z){ //rotations assuming fixed z
        //x,y; -y,x; -x,-y; y,-x (note: cannot have y,x or -y,-x as that would be a reflection = invalid)
        return new int[][]{{x,y,z},{-y,x,z},{-x,-y,z},{y,-x,z}};
    }

    static ArrayList<Scnr> readInput(Scanner s){
        ArrayList<Scnr> scanners = new ArrayList<>();
        int index = 0;
        s.nextLine();
        ArrayList<ArrayList<Integer>> ob = new ArrayList<>();
        while(s.hasNextLine()){
            String line = s.nextLine();
            if(line.equals("")){
                scanners.add(new Scnr(index, ob));
                ob = new ArrayList<>();
                index++;
                s.nextLine();
            }
            else{
                ArrayList<Integer> coords = new ArrayList<>();
                String[] l = line.split(",");
                for(String c: l){
                    coords.add(Integer.parseInt(c));
                }
                ob.add(coords);
            }
        }
        return scanners;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String delimiter = System.getProperty("file.separator");
        Scanner s = new Scanner(new File("Solutions" + delimiter + "data21" + delimiter + "day19"));
        ArrayList<Scnr> scanners = readInput(s);

        Scnr current = scanners.get(0);
        current.fixed = current.original;
        current.pos = new int[]{0,0,0};

        ArrayList<Scnr> nonempty = new ArrayList<>();
        while(!scanners.isEmpty()){
            for(Scnr sc: scanners){
                if(!sc.fixed.isEmpty()){
                    continue;
                }
                for(Scnr ss: scanners){
                    sc.overlap12(ss);
                }
            }
        }

        HashSet<ArrayList<Integer>> beacons = new HashSet<>();
        for(Scnr ss: scanners){
            for(ArrayList<Integer> fixed: ss.fixed){
                beacons.add(fixed);
            }
        }

    }

    static class Scnr{
        int n; //scanner number
        int[][] observed; //observed beacons, all possible orientations
        HashSet<ArrayList<Integer>> original;
        HashSet<ArrayList<Integer>> fixed; //fixed beacons
        int[] pos; //global position, relative to 0 (or empty if not calculated yet)

        public Scnr(int num, ArrayList<ArrayList<Integer>> ob){
            n = num;
            observed = new int[ob.size() * 24][3];
            original = new HashSet(ob);

            int i = 0;
            for(ArrayList<Integer> o: ob){
                int[][] allOrie = orientations(o.get(0), o.get(1), o.get(2));
                for(int[] ao: allOrie){
                    observed[i] = ao;
                    i++;
                }
            }
            fixed = new HashSet<>();
            pos = new int[3];
        }

        public boolean overlap12(Scnr other){ //compare the observed of this to the fixed beacons of other
            //always deal with overlap for one scanner you've already "dealt" with
            // starting with scanner 0 which is by definition done
            for(int[] orientation: observed){ //brute force let's go
                for(ArrayList<Integer> fixed: other.fixed){
                    int dx = fixed.get(0) - orientation[0];
                    int dy = fixed.get(1) - orientation[1];
                    int dz = fixed.get(2) - orientation[2];
                    HashSet<ArrayList<Integer>> shifted = new HashSet<>();
                    for(int i =0; i< observed.length; i++){
                        ArrayList<Integer> next = new ArrayList<>();
                        next.add(observed[i][0] + dx);
                        next.add(observed[i][1] + dy);
                        next.add(observed[i][2] + dz);
                        shifted.add(next);
                    }
                    HashSet<ArrayList<Integer>> common = new HashSet<>(shifted);
                    common.retainAll(other.fixed); //intersection
                    System.out.println(common.size());
                    if(common.size() >= 12){
                        this.fixed = shifted;
                        this.pos = new int[]{dx, dy, dz};
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
