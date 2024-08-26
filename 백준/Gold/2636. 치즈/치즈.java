import java.util.*;
import java.io.*;

public class Main {

    static int R, C;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0; // 시간이 지난 횟수
        int lastCheese = 0; // 마지막에 남아있던 치즈 조각의 수

        while (true) {
            visited = new boolean[R][C];
            int cheeseCount = getCheeseCount(); // 현재 남아있는 치즈의 수

            if (cheeseCount == 0) {
                break; // 치즈가 모두 녹으면 종료
            }

            lastCheese = cheeseCount; // 마지막 치즈 수를 기록
            bfs(0, 0); // 외곽 공기를 기준으로 BFS 수행하여 치즈 녹이기
            time++; // 시간 증가
        }

        sb.append(time).append("\n").append(lastCheese);
        System.out.println(sb.toString());
    }

    public static void bfs(int r, int c) {
        ArrayDeque<int[]> deque = new ArrayDeque<>();
        visited[r][c] = true;
        deque.offer(new int[]{r, c});

        while (!deque.isEmpty()) {
            int[] cur = deque.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];

                if (check(nr, nc)) {
                    visited[nr][nc] = true;
                    if (map[nr][nc] == 0) {
                        deque.offer(new int[]{nr, nc}); // 외부 공기 탐색
                    } else if (map[nr][nc] == 1) {
                        map[nr][nc] = 0; // 치즈 녹이기
                    }
                }
            }
        }
    }

    public static boolean check(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C && !visited[r][c];
    }

    public static int getCheeseCount() {
        int count = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }
}