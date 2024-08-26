import java.util.*;
import java.io.*;

public class Solution {

    static int N, maxVal;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            maxVal = Integer.MIN_VALUE;
            for (int day = 0; day <= 100; day++) {
                visited = new boolean[N][N]; // 매일 초기화
                eatCheese(day);
                int cheeseGroup = 0;

                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (!visited[i][j] && map[i][j] != -1) {
                            bfs(i, j);
                            cheeseGroup++;
                        }
                    }
                }

                maxVal = Math.max(maxVal, cheeseGroup);
            }

            sb.append("#").append(tc).append(" ").append(maxVal).append("\n");

        }
        System.out.println(sb);

    }

    public static void eatCheese(int day) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == day) {
                    map[i][j] = -1;
                }
            }
        }
    }

    public static void bfs(int r, int c) {
        ArrayDeque<int[]> deque = new ArrayDeque<>();
        visited[r][c] = true;
        deque.offer(new int[] { r, c });

        while (!deque.isEmpty()) {
            int[] cur = deque.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];

                if (check(nr, nc)) {
                    visited[nr][nc] = true;
                    deque.offer(new int[] { nr, nc });
                }

            }
        }
    }

    public static boolean check(int r, int c) {
        if (r >= 0 && c >= 0 && r < N && c < N && map[r][c] != -1 && !visited[r][c]) {
            return true;
        }
        return false;
    }

}
