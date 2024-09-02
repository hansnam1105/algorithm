import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main {

    static int N, minVal;
    static int[][] map;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = parseInt(st.nextToken());
            }
        }
        minVal = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            visited[i] = true;
            dfs(i, i, 0);
        }

        System.out.println(minVal);

    }

    public static void dfs(int start, int cur, int cost) {
        if (allVisited()) {
            if (map[cur][start] != 0) {
                minVal = Math.min(minVal, cost + map[cur][0]);
            }
        }
        for (int i = 1; i < N; i++) {
            if (!visited[i] && map[cur][i] != 0) {
                visited[i] = true;
                dfs(start, i, cost + map[cur][i]);
                visited[i] = false;
            }
        }
    }

    public static boolean allVisited() {
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }
}
