package util;

import java.util.Comparator;
import java.util.PriorityQueue;

public class GraphAlgos {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class GridNode{
        int x;
        int y;
        int d;
        GridNode(int a, int b, int distance){
            x = a;
            y = b;
            d = distance;
        }
    }
    static Comparator<GridNode> dComp = (o1, o2) -> o1.d > o2.d ? 1 : o1.d < o2.d ? -1 : 0;


    static int[][] grid;
    static boolean validIndex(int x, int y){
        return x >= 0 && y >= 0 && x < grid.length && y < grid[0].length;
    }

    public static int dijkstra(int[][] g){
        grid = g;
        int rows = g.length;
        int cols = g[0].length;
        int[][] dist = new int[rows][cols];
        for(int i =0; i<rows; i++){
            for(int j =0; j<cols; j++){
                dist[i][j] = i == 0 && j == 0 ? 0 : Integer.MAX_VALUE;
            }
        }
        PriorityQueue<GridNode> pq = new PriorityQueue<>(dComp);
        GridNode start = new GridNode(0,0,0);

        pq.add(start);
        while(!pq.isEmpty()){
            GridNode current = pq.poll(); 
            for(int i =0; i<4; i++){
                int x = current.x + dx[i]; 
                int y = current.y + dy[i];
                if(validIndex(x, y)){
                    int pathA = dist[x][y];
                    int pathB = dist[current.x][current.y] + grid[x][y];
                    if(pathA > pathB){
                        if(pathA != Integer.MAX_VALUE){
                            GridNode adjusted = new GridNode(x, y, dist[x][y]); // remove old version
                            pq.remove(adjusted);
                        }
                        dist[x][y] = pathB;
                        GridNode toAdd = new GridNode(x, y, pathB);
                        pq.add(toAdd);
                    }
                }
            }
        }
        return dist[rows-1][cols-1];
    }
}
