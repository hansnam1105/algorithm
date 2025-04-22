import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int M;
    static int V;
    static int[][] map;
    static boolean[] visited;
    static Queue<Integer> q = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            map[x][y] = map[y][x] = 1;
        }

        visited = new boolean[N+1];
        dfs(V);
        sb.append("\n");
        visited = new boolean[N+1];
        bfs(V);

        System.out.println(sb);

    }

    private static void dfs(int start) {
        visited[start] = true;
        sb.append(start).append(" ");
        for(int i=0; i<=N; i++){
            if(map[start][i] == 1 && !visited[i]){
                dfs(i);
            }
        }
    }

    private static void bfs(int start){
        visited[start] = true;
        q.add(start);

        while(!q.isEmpty()){
            start = q.poll();
            sb.append(start).append(" ");
            for(int i=1; i<=N; i++) {
                if (map[start][i] == 1 && !visited[i]) {
                    q.add(i);
                    visited[i] = true;
                }
            }

        }

    }
}