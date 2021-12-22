package year2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Day22 {
    static ArrayList<Cube> cubes = new ArrayList<>();
    public static void main(String[] args) throws FileNotFoundException {
        String delimiter = System.getProperty("file.separator");
        Scanner s = new Scanner(new File("Solutions" + delimiter + "data21" + delimiter + "day22"));
        while(s.hasNextLine()){
            String[] info = s.nextLine().split(" ");
            String[] ranges = info[1].split(",");

            boolean on = info[0].equals("on") ? true: false;

            int x1 = Integer.parseInt(ranges[0].substring(2, ranges[0].indexOf("..")));
            int x2 = Integer.parseInt(ranges[0].substring(ranges[0].indexOf("..") + 2));

            int y1 = Integer.parseInt(ranges[1].substring(2, ranges[1].indexOf("..")));
            int y2 = Integer.parseInt(ranges[1].substring(ranges[1].indexOf("..") + 2));

            int z1 = Integer.parseInt(ranges[2].substring(2, ranges[2].indexOf("..")));
            int z2 = Integer.parseInt(ranges[2].substring(ranges[2].indexOf("..") + 2));

            Cube next = new Cube(Math.min(x1, x2), Math.max(x1, x2) +1,
                    Math.min(y1, y2), Math.max(y1, y2)+1,
                    Math.min(z1, z2), Math.max(z1, z2)+1);

            removeCubeFromCubes(next);

            if(on){
                cubes.add(next);
            }

        }
        System.out.println(cubes.size());
        System.out.println(volume());
    }

    static BigInteger volume(){
        BigInteger count = BigInteger.ZERO;
        for(Cube c: cubes){
            count = count.add(c.volume());
        }
        return count;
    }

    static void removeCubeFromCubes(Cube toRemove){
        ArrayList<Cube> added = new ArrayList<>();

        for(int i =0; i<cubes.size(); i++){
            Cube current = cubes.get(i);
            if(intersect(toRemove, current)){
                if(current.x1 < toRemove.x1){
                    added.add(new Cube(current.x1, toRemove.x1, current.y1, current.y2, current.z1, current.z2));
                    current.x1 = toRemove.x1;
                }
                if(current.x2 > toRemove.x2){
                    added.add(new Cube(toRemove.x2, current.x2, current.y1, current.y2, current.z1, current.z2));
                    current.x2 = toRemove.x2;
                }
                if(current.y1 < toRemove.y1){
                    added.add(new Cube(current.x1, current.x2, current.y1, toRemove.y1, current.z1, current.z2));
                    current.y1 = toRemove.y1;
                }
                if(current.y2 > toRemove.y2){
                    added.add(new Cube(current.x1, current.x2, toRemove.y2, current.y2, current.z1, current.z2));
                    current.y2 = toRemove.y2;
                }
                if(current.z1 < toRemove.z1){
                    added.add(new Cube(current.x1, current.x2, current.y1, current.y2, current.z1, toRemove.z1));
                    current.z1 = toRemove.z1;
                }
                if(current.z2 > toRemove.z2){
                    added.add(new Cube(current.x1, current.x2, current.y1, current.y2, toRemove.z2, current.z2));
                    current.z2 = toRemove.z2;
                }

            }
            else{
                added.add(current);
            }
        }
        cubes = added;
    }

    static boolean intersect(Cube c1, Cube c2){
        return c1.x1 < c2.x2 && c1.x2 > c2.x1
                && c1.y1 < c2.y2 && c1.y2 > c2.y1
                && c1.z1 < c2.z2 && c1.z2 > c2.z1;
    }
    static class Cube{
        int x1, x2, y1, y2, z1, z2;

        public Cube(int a, int b, int c, int d, int e, int f){
            x1 = a;
            x2 = b;
            y1 = c;
            y2 = d;
            z1 = e;
            z2 = f;
        }

        public BigInteger volume(){
            BigInteger b1 = new BigInteger((x2-x1) + "");
            BigInteger b2 = new BigInteger((y2-y1) + "");
            BigInteger b3 = new BigInteger((z2-z1) + "");
            return b1.multiply(b2).multiply(b3);
        }
    }

}
