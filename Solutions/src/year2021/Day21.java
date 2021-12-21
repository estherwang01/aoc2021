package year2021;

public class Day21 {
    static int p1 = 7;
    static int p2 = 9;

    static int c1 = 0;
    static int c2 = 0;

    static int counter = 1;
    static int rolls = 0;

    static int[] possibleRolls = {
            3, 4, 4, 4, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 8, 8, 8, 9
    }; //various rolls you could get with 3 die

    static LongPair[][][][] dp = new LongPair[11][11][21][21];
    // indices: p1, p2, c1, c2 (0 indices useless)

    static LongPair part2(int p1, int p2, int c1, int c2){
        //p1 = player making a turn
        if(c1 >= 21){
            return new LongPair(1,0);
        }
        else if(c2 >= 21){
            return new LongPair(0, 1);
        }
        else if(dp[p1][p2][c1][c2] == null){
            long x = 0;
            long y = 0;
            for(int roll: possibleRolls){
                int played1 = (p1+roll - 1) % 10 + 1;
                LongPair next = part2(p2, played1, c2, c1 + played1);
                x += next.y; //p2 active player in above call so reverse x and y
                y += next.x;
            }
            dp[p1][p2][c1][c2] = new LongPair(x,y);
        }
        return dp[p1][p2][c1][c2];
    }

    //p1 turns
    public static void turn(){
        int r =  counter + (counter % 100) + 1 + (counter + 1) % 100 + 1;
        counter += 3;

        if(rolls % 2 == 0){
            p1 = (r + p1 - 1) % 10 + 1;
            c1 += p1;
        }else{
            p2 = (r + p2 - 1) % 10 + 1;
            c2 += p2;
        }
        rolls++;
    }
    public static void main(String[] args)  {
        while(c1 < 1000 && c2 < 1000){
            turn();
        }
        System.out.println(Math.min(c1, c2) * rolls * 3); // p1
        LongPair p2result = part2(7, 9, 0, 0);
        System.out.println(Math.max(p2result.x, p2result.y)); // p2
    }

    static class LongPair{
        long x;
        long y;
        public LongPair(long a, long b){
            x = a;
            y = b;
        }
    }
}
