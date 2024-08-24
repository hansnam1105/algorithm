import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main {

    static int R, C, ans;
    static char[][] map;
    static int[][] dir = { { -1, 1 }, { 0, 1 }, { 1, 1 } };
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = parseInt(st.nextToken());
        C = parseInt(st.nextToken());

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }
        visited = new boolean[R][C];
        ans = 0;

        for (int i = 0; i < R; i++) {
            if (dfs(i, 0)) { 
                ans++;
            }
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
    
    public static boolean dfs(int r, int c) {
        if (c == C - 1) {
            return true;
        }

        for (int d = 0; d < 3; d++) {
            int nr = r + dir[d][0];
            int nc = c + dir[d][1];
            if (check(nr, nc)) {
                visited[nr][nc] = true;
                if (dfs(nr, nc)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean check(int r, int c) {
        if (r >= 0 && c >= 0 && r < R && c < C && map[r][c] == '.' && !visited[r][c]) {
            return true;
        }
        return false;
    }
}
