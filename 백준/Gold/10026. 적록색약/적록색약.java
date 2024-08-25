import java.io.*;

public class Main {

    static int N;
    static char[][] map;
    static boolean[][] visited;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int normCnt = 0;
        int colorBlindCnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    dfs(i, j, map[i][j]);
                    normCnt++;
                }
            }
        }

        // 적록색약인 경우 G->R
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'G') {
                    map[i][j] = 'R';
                }
            }
        }

        // 적록색약인 경우
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    dfs(i, j, map[i][j]);
                    colorBlindCnt++;
                }
            }
        }

        bw.write(normCnt + " " + colorBlindCnt);
        bw.flush();
        bw.close();
        br.close();

    }
    
    public static void dfs(int y, int x, char color) {
        visited[y][x] = true;
        
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                if (!visited[ny][nx] && map[ny][nx] == color) {
                    dfs(ny, nx, color);
                }
            }
        }
    }
}
