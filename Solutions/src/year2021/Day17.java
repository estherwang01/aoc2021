package year2021;

public class Day17 {
    static int x1, x2, y1, y2, vyLowerBound, vyUpperBound, vxLowerBound, vxUpperBound;
    public static void main(String[] args) {
        x1 = 94; //left
        x2 = 151; //right
        y1 = -156; // lower
        y2 = -103; // upper

        //p1: (-y1 - 1)(-y1)/2
        // Reasoning: target box is in the negative y zone. Want to find max y velocity to maximize height
        // At some point will pass back through y=0 with negative initial y velocity
        // In the next step, velocity increases in magnitude by 1, so initial must be 1 less than magnitude
        //of lower bound

        //p2: math too hard i give up. tiny bit of math to reduce the number of things to check:
        // part 1 gives upper bound on y velocity
        // lower bound on y velocity is y1
        //upper bound on x velocity is x2 (cannot go past it in the first step immediately)
        // lower bound on x velocity is found by solving a(a+1)/2 = x1 where a = x velocity

        vyLowerBound = y1;
        vyUpperBound = (-y1-1);
        vxLowerBound = (int) Math.ceil(0.5 * (-1 + Math.sqrt(1 + 8 *x1)));
        vxUpperBound = x2;

        int count = 0;
        for(int i = vxLowerBound; i<=vxUpperBound; i++){
            for(int j = vyLowerBound; j<=vyUpperBound; j++){
                count += check(i,j);
            }
        }
        System.out.println(count);

    }
    public static int check(int vx, int vy){ // this is kinda dependent on the target being in positive x, negative y
        // whoops. maybe rewrite in the future to not be. or just solve the goddamn math. smh
        int x = 0;
        int y = 0;
        while(x <= x2 && y >= y1){
            if(x >= x1 && y <= y2){
                return 1;
            }
            x +=vx;
            y += vy;
            vx = Math.max(vx-1, 0);
            vy = vy-1;
        }
        return 0;
    }
}
