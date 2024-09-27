import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {
    static int[][] map;
    static boolean[][] visited;
    static List<Point> cheeseList;
    static int N, M, cheeseCnt;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        cheeseList = new ArrayList<>();
        cheeseCnt = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1){
                    cheeseList.add(new Point(i,j));
                    cheeseCnt++;
                }
            }
        }

        int time = 0;
        while (cheeseCnt > 0) {
            visited = new boolean[N][M];
            bfs();
            meltCheese();
            time++;
        }

        bw.write(String.valueOf(time));
        bw.flush();
        bw.close();
    }

    // BFS 외곽 확인
    static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            int r = current.x, c = current.y;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc]) continue;

                if (map[nr][nc] == 0) {
                    queue.add(new Point(nr, nc));
                    visited[nr][nc] = true;
                }
            }
        }
    }
        
    static void meltCheese() {
        List<Point> meltedCheese = new ArrayList<>();

        for (Point cheese : cheeseList) {
            int r = cheese.x;
            int c = cheese.y;
            int airCnt = 0;

            // Count the number of sides exposed to air
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (nr >= 0 && nc >= 0 && nr < N && nc < M && visited[nr][nc]) {
                    airCnt++;
                }
            }

            if (airCnt >= 2) {
                meltedCheese.add(cheese);
            }
        }

        for (Point cheese : meltedCheese) {
            map[cheese.x][cheese.y] = 0;
            cheeseList.remove(cheese);
            cheeseCnt--;
        }
    }
}
