import java.util.*;
import java.awt.Point;

class Solution {
    
    private boolean[][] visited;
    private int[] dr = {-1, 1, 0, 0};
    private int[] dc = {0, 0, -1, 1};
    private int maxOil, row, col;
    private Map<Integer, Integer> oilMap;
    private Map<Integer, Integer> maxMap;
    private int[][] labels;
    
    public int solution(int[][] land) {
        row = land.length;
        col = land[0].length;
        maxOil = 0;
        labels = new int[row][col];
        oilMap = new HashMap<>();
        visited = new boolean[row][col];
        int labelCount = 0;
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                if(land[i][j] == 1 && !visited[i][j]){
                    labelCount++;
                    int size = bfs(land, i, j, labelCount);
                    oilMap.put(labelCount, size);
                }
            }
        }
        for(int i=0; i<col; i++){
            Set<Integer> labelSet = new HashSet<>();
            for(int j=0; j<row; j++){
                if(labels[j][i] != 0){
                    labelSet.add(labels[j][i]);
                }
            }
            int sum = 0;
            for(int label : labelSet){
                sum += oilMap.get(label);
            }
            maxOil = Math.max(maxOil, sum);
        }
        
        return maxOil;
    }
    
    private int bfs(int[][] map, int r, int c, int labelCount){
        int oil = 1;
        Queue<Point> q = new LinkedList<>();
        visited[r][c] = true;
        q.offer(new Point(r, c));
        labels[r][c] = labelCount;
        while(!q.isEmpty()){
            Point cur = q.poll();
            for(int d=0; d<4; d++){
                int nr = cur.x + dr[d];
                int nc = cur.y + dc[d];
                if(check(map, nr, nc)){
                    visited[nr][nc] = true;
                    oil++;
                    q.offer(new Point(nr,nc));
                    labels[nr][nc] = labelCount;
                }
            }
        }
    
        return oil;
    }
    
    private boolean check(int[][] map, int r, int c){
        return (r>=0 && r<row && c>=0 && c<col && !visited[r][c] && map[r][c] != 0);
    }
}