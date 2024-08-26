import java.util.*;
import java.io.*;

public class Main {

    static int R, C, max;
    static char[][] map;
    static boolean[] visited;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[26]; // 알파벳은 총 26개이므로 크기는 26
        max = 0;

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        dfs(0, 0, 1); // 시작점에서 dfs 시작, 시작점을 포함하여 count를 1로 시작

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
        br.close();
    }

    // DFS
    public static void dfs(int r, int c, int count) {
        visited[map[r][c] - 'A'] = true; // 현재 알파벳 방문 표시

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (check(nr, nc) && !visited[map[nr][nc] - 'A']) {
                dfs(nr, nc, count + 1);
            }
        }

        visited[map[r][c] - 'A'] = false; // 방문 해제
        max = Math.max(max, count);
    }

    public static boolean check(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }
}
