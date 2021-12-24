package year2021;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Day19 {
    static ArrayList<Scnr> readInput(Scanner s){
        ArrayList<Scnr> scanners = new ArrayList<>();
        int index = 0;
        s.nextLine();
        ArrayList<Point3> ob = new ArrayList<>();
        while(s.hasNextLine()){
            String line = s.nextLine();
            if(line.equals("")){
                scanners.add(new Scnr(index, ob));
                ob = new ArrayList<>();
                index++;
                s.nextLine();
            }
            else{
                String[] l = line.split(",");
                ob.add(new Point3(Integer.parseInt(l[0]), Integer.parseInt(l[1]), Integer.parseInt(l[2])));
            }
        }
        scanners.add(new Scnr(index, ob));
        return scanners;
    }
    //generate all 24 orientations given an x,y,z coordinate
    static ArrayList<Point3> allOrientations(int x, int y, int z){
        ArrayList<Point3> all = new ArrayList<>();
        //for each possible orientation, get all the rotations of it
        for(Point3 or: orientations(x,y,z)){
            ArrayList<Point3> rot =rotations(or.x, or.y, or.z);
            for(Point3 orRot: rot){
                all.add(orRot);
            }
        }
        return all;
    }
    static ArrayList<Point3> orientations(int x, int y, int z){ //all the different z orientations
        //z facing z, x, y, -z, -y, -x in that order
        ArrayList<Point3> add = new ArrayList<>();
        int[][] coords =  new int[][]{{x,y,z},{z,y,-x}, {x,z,-y}, {x,-y,-z},
                {x,-z,y}, {-z,y,x}};
        for(int[] coord: coords){
            add.add(new Point3(coord[0], coord[1], coord[2]));
        }
        return add;
    }
    static ArrayList<Point3> rotations(int x, int y, int z){ //rotations assuming fixed z
        //x,y; -y,x; -x,-y; y,-x (note: cannot have y,x or -y,-x as that would be a reflection = invalid)
        // oh my fucking god why was it so hard for me to come up with this
        // i hate this stupid function
        ArrayList<Point3> add = new ArrayList<>();
        int[][] coords =  new int[][]{{x,y,z},{-y,x,z},{-x,-y,z},{y,-x,z}};
        for(int[] coord: coords){
            add.add(new Point3(coord[0], coord[1], coord[2]));
        }
        return add;
    }

    public static void compare(Scnr current, ArrayList<Scnr> others){
        if(current.fixed == null){
            for(Scnr scanner: others){
                if(scanner.fixed != null && current.overlap12(scanner)){
                    return;
                }
            }
        }
    }

    public static int distance(Scnr a, Scnr b){
        return Math.abs(a.pos[0] - b.pos[0]) +Math.abs(a.pos[1] - b.pos[1]) + Math.abs(a.pos[2] - b.pos[2]);
    }

    public static void main(String[] args) throws FileNotFoundException {
        String delimiter = System.getProperty("file.separator");
        Scanner s = new Scanner(new File("Solutions" + delimiter + "data21" + delimiter + "day19"));
        ArrayList<Scnr> scanners = readInput(s);

        int total = scanners.size();
        HashSet<Scnr> fixedScanners = new HashSet<>();

        while(fixedScanners.size() < total){
            for(Scnr scanner: scanners){
                compare(scanner, scanners);
                if(scanner.fixed != null){
                    fixedScanners.add(scanner);
                }
            }
        }

        HashSet<Point3> beacons = new HashSet<>();
        for(Scnr scnr: scanners){
            for(Point3 p: scnr.fixed){
                beacons.add(p);
            }
        }

        int largest = 0;
        for(int i = 0; i<scanners.size(); i++){
            for(int j =i + 1; j<scanners.size(); j++){
                largest = Math.max(largest, distance(scanners.get(i), scanners.get(j)));
            }
        }

        System.out.println(beacons.size()); // part 1
        System.out.println(largest); //part 2
    }

    static class Scnr{
        int n; //scanner number
        ArrayList<ArrayList<Point3>> observed;
        ArrayList<Point3> fixed;
        int[] pos; //global position, relative to 0 (or empty if not calculated yet)

        public Scnr(int num, ArrayList<Point3> ob){
            n = num;
            observed = new ArrayList<>();
            for(int i = 0; i< 24; i++){
                observed.add(new ArrayList<>());
            }
            for(Point3 o: ob){
                ArrayList<Point3> allOrie = allOrientations(o.x, o.y, o.z);
                for(int i =0; i<24; i++){
                    observed.get(i).add(allOrie.get(i));
                }
            }
            if(num == 0){
                fixed = observed.get(0);
                pos = new int[]{0,0,0};
            }
            else {
                fixed = null;
                pos = null;
            }
        }

        public boolean overlap12(Scnr other){ //compare the observed of this to the fixed beacons of other
            //always deal with overlap for one scanner you've already "dealt" with
            // starting with scanner 0 which is by definition done
            for(ArrayList<Point3> orientation: observed){
                for(Point3 fix: other.fixed){
                    for(Point3 or: orientation){
                        int dx = fix.x - or.x;
                        int dy = fix.y - or.y;
                        int dz = fix.z - or.z;

                        ArrayList<Point3> shifted = new ArrayList<>();
                        for(Point3 allPoints: orientation){
                            shifted.add(new Point3(allPoints.x + dx, allPoints.y + dy, allPoints.z+dz));
                        }

                        ArrayList<Point3> common = new ArrayList<>();
                        for(Point3 shift: shifted){
                            if(other.fixed.contains(shift)){
                                common.add(shift);
                            }
                        }
                        if(common.size() >= 12){
                            this.fixed = shifted;
                            this.pos = new int[]{dx, dy, dz};
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }

    static class Point3{
        int x,y,z;
        public Point3(int a, int b, int c){
            x = a;
            y = b;
            z = c;
        }
        @Override
        public String toString(){
            return "(" + x + "," + y + "," + z + ")";
        }
        @Override
        public boolean equals(Object other){
            Point3 o = (Point3) other;
            return x == o.x && y == o.y && z == o.z;
        }
        @Override
        public int hashCode(){
            return x<<16 + y<<8 + z;
        }
    }
}
