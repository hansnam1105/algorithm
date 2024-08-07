import java.util.*;
import java.io.*;

public class Main {

    static int N, M, minRoute;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String temp = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = temp.charAt(j) - '0';
            }
        }
        visited = new boolean[N][M];
        bfs(0, 0);
        sb.append(map[N-1][M-1]);
        System.out.println(sb);

    }
    
    public static void bfs(int y, int x) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { y, x });
        visited[y][x] = true;


        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curx = cur[1];
            int cury = cur[0];
            for(int i=0; i<4; i++){
                int nx = curx + dx[i];
                int ny = cury + dy[i];
                if(nx < 0 || nx >= M || ny < 0 || ny >= N){
                    continue;
                }
                if(!visited[ny][nx] && map[ny][nx] == 1){
                    visited[ny][nx] = true;
                    q.add(new int[] {ny, nx});
                    map[ny][nx] = map[cury][curx]+1;

                }
            }
        }

    }
}
