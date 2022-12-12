package year2022;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class Day11 {
    static BiFunction<String, Long, LongFunction<Long>> produceOp = (i, j) -> i.equals("+") ? (x) -> x + j : i.equals("-") ? x -> x - j : i.equals("*") ? x -> x*j : x -> x/j;
    static class Monkey {
        List<Long> values = new ArrayList<>();;
        LongFunction<Long> op;
        int div, index, t, f;
        public Monkey(String s){
            String[] info = s.split("\n");
            index = Integer.parseInt(info[0].split(" ")[1].substring(0,1));
            values = Arrays.asList(info[1].substring(info[1].indexOf(":") + 1).trim().split(", ")).stream().map(x -> Long.parseLong(x)).collect(Collectors.toList());
            String[] ops = info[2].trim().split(" ");
            if(ops[5].trim().equals("old"))op = (x) -> x*x;
            else op = produceOp.apply(ops[4], Long.parseLong(ops[5]));
            div = Integer.parseInt(info[3].split(" ")[3]);
            t = Integer.parseInt(info[4].split(" ")[5]);
            f = Integer.parseInt(info[5].split(" ")[5]);
        }
    }

    public static long play(int rounds, List<Monkey> monkeys, Function<Long, Long> f){
        List<Long> count = new ArrayList<>();
        for(int i =0; i<monkeys.size(); i++){
            count.add(0L);
        }
        for(int i = 1; i<=rounds; i++){
            for(int j = 0; j<monkeys.size(); j++){
                Monkey m = monkeys.get(j);
                count.set(j, count.get(j) + m.values.size());
                for(int x = 0; x<m.values.size();){
                    long k = m.values.get(x);
                    long worry = f.apply(m.op.apply(k));
                    if(worry % m.div == 0) monkeys.get(m.t).values.add(worry);
                    else monkeys.get(m.f).values.add(worry);
                    m.values.remove(0);
                }
            }
        }
        Collections.sort(count);
        return count.get(count.size()-1)*count.get(count.size()-2);

    }

    public static void main(String[] args) {
        try {
            File day1 = new File("Solutions\\data22\\day11input");
            BufferedReader br = new BufferedReader(new FileReader(day1));
            String input = "";
            String line = br.readLine();
            while(line != null){
                input += line.strip() + "\n";
                line = br.readLine();
            }
            String[] ms = input.split("\n\n");
            ArrayList<Monkey> monkeys = new ArrayList<>();
            long mod = 1;
            for(String m: ms){
                Monkey next = new Monkey(m);
                monkeys.add(next);
                mod *= next.div;
            }
            final long m = mod;

//            System.out.println(play(20, monkeys, (x) -> x/3));
            System.out.println(play(10000, monkeys, (x) -> x % m));

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
