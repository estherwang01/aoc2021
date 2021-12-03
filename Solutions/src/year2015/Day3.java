package year2015;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;

class Pair{
    int x;
    int y;
    public Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
    public Pair north(){
        return new Pair(x, y+1);
    }
    public Pair west(){
        return new Pair(x-1, y);
    }
    public Pair east(){
        return new Pair(x+1, y);
    }
    public Pair south(){
        return new Pair(x, y-1);
    }
    @Override
    public boolean equals(Object o){
        Pair other = (Pair) o;
        return this.x == other.x && this.y == other.y;
    }
    @Override
    public int hashCode(){
        return y << 16 + x;
    }
}
public class Day3 {

    public static void main(String[] args) {

        HashSet<Pair> visited = new HashSet<>();

        try{
            File day1 = new File("Solutions\\data15\\day3");
            BufferedReader br = new BufferedReader(new FileReader(day1));
            Pair santa = new Pair(0,0);
            Pair robo = new Pair(0,0);
            int next = br.read();
            int index = 0;

            while(next != -1){
                Pair active = (index % 2 == 0) ? santa : robo;
                char n = (char) next;
                switch (n) {
                    case '^':
                        visited.add(active.north());
                        active = active.north();
                        break;
                    case 'v':
                        visited.add(active.south());
                        active = active.south();
                        break;
                    case '>':
                        visited.add(active.east());
                        active = active.east();
                        break;
                    case '<':
                        visited.add(active.west());
                        active = active.west();
                        break;
                }
                if(index % 2 == 0){
                    santa = active;
                }else{
                    robo = active;
                }
                index++;
                next = br.read();
            }
            System.out.println(visited.size());

        }catch(Exception e){

        }
    }
}
