import java.util.*;
import java.awt.Point;

class Solution {
    private int[] dr = {0, 0, 1, -1};
    private int[] dc = {1, -1, 0, 0};
    
    public int solution(int[][] maps) {
        int answer = 0;
        
        
        return bfs(maps);
    }
    
    private int bfs(int[][] maps){
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0,0));
        int[][] visited = new int[maps.length][maps[0].length];
        visited[0][0] = 1;
        
        while(!q.isEmpty()){
            Point cur = q.poll();
            
            if(cur.x == maps.length-1 && cur.y == maps[0].length -1){
                return visited[cur.x][cur.y];
            }
            
            for(int i=0; i<4; i++){
                int nr = cur.x + dr[i];
                int nc = cur.y + dc[i];
                
                if(nr < 0 || nr >= maps.length || nc < 0 || nc >= maps[0].length) continue;
                if(visited[nr][nc] != 0 || maps[nr][nc] == 0) continue;
                q.add(new Point(nr, nc));
                visited[nr][nc] = visited[cur.x][cur.y] + 1;
            }
        }
        return -1;

    }
}