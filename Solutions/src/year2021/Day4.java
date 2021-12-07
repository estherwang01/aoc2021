package year2021;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day4 {
    public static boolean hasWon(Pair[][] board){
        for(int i =0; i<board.length; i++){
            int count = 0;
            for(int j =0; j<board[0].length; j++){
                if(board[i][j].y){
                    count++;
                }
            }
            if(count == board.length){
                return true;
            }
        }
        for(int i =0; i<board[0].length; i++){
            int count = 0;
            for(int j =0; j<board.length; j++){
                if(board[j][i].y){
                    count++;
                }
            }
            if(count == board.length){
                return true;
            }
        }
        return false;
    }

    public static int getNonSum(Pair[][] board){
        int sum = 0;
        for(int i =0; i<board.length; i++){
            for(int j =0; j<board[0].length; j++){
                if(!board[i][j].y){
                    sum += board[i][j].x;
                }
            }
        }
        return sum;
    }

    public static void updateBoard(Pair[][] board, int num){
        for(int i =0; i<board.length; i++){
            for(int j =0; j<board[0].length; j++){
                if(board[i][j].x == num){
                    board[i][j] = new Pair(num, true);
                }
            }
        }
    }

    public static int play(String[] line1, ArrayList<Pair[][]> boards){
        for(int i =0; i<line1.length; i++){
            int num = Integer.parseInt(line1[i]);
            for(int x = 0; x<boards.size(); x++){
                updateBoard(boards.get(x), num);
                if(hasWon(boards.get(x))){
                    if(boards.size() == 1){
                        int sum = getNonSum(boards.get(0));
                        return sum * num;
                    }
                    boards.remove(x);
                    x--;
                }

            }
        }
        return -1;
    }
    public static void main(String[] args) {
        try{
            Scanner s = new Scanner(new File("Solutions\\data21\\day4"));
            String[] line1 = s.nextLine().split(",");

            ArrayList<Pair[][]> boards = new ArrayList<>();

            while(s.hasNextLine()){
                s.nextLine();
                Pair[][] board = new Pair[5][5];
                for(int i =0; i<board.length; i++){
                    String n = s.nextLine().trim().replaceAll("  ", " ");
                    System.out.println(n);
                    String[] next = n.split(" ");
                    for(int j = 0; j<next.length; j++){
                        board[i][j] = new Pair(Integer.parseInt(next[j]), false);
                    }
                }
                boards.add(board);
            }
            System.out.println("here");
            int result = play(line1, boards);
            System.out.println(result);

        }catch(Exception e){
            e.printStackTrace();

        }
    }
}
class Pair{
    int x;
    boolean y;
    public Pair(int a, boolean b){
        x = a;
        y = b;
    }
}
