import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main{
    
    static int N, M;
    static boolean[][][] visited;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        
        for(int i=0; i<N; i++){
            String temp = br.readLine();
            for(int j=0; j<M; j++){
                map[i][j] = temp.charAt(j) - '0';
            }
        }
        int result = bfs();
        
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        
    }
    
    static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        visited = new boolean[N][M][2];
        // r, c, 거리, 벽 부셨는지 (0 안 부숨, 1 부숨)
        queue.offer(new int[] {0, 0, 1, 0});
        visited[0][0][0] = true;
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];
            int dist = cur[2];
            int wallBroken = cur[3];
            
            if (r == N - 1 && c == M - 1) {
                return dist; // 도착지에 도달
            }
            
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                
                // 벽 부수지 않고 이동
                if (map[nr][nc] == 0 && !visited[nr][nc][wallBroken]) {
                    visited[nr][nc][wallBroken] = true;
                    queue.offer(new int[] {nr, nc, dist + 1, wallBroken});
                }
                
                // 벽 부수고 이동
                if (map[nr][nc] == 1 && wallBroken == 0 && !visited[nr][nc][1]) {
                    visited[nr][nc][1] = true;
                    queue.offer(new int[] {nr, nc, dist + 1, 1});
                }
            }
        }
        
        return -1;
    }
}