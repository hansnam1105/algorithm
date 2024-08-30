import java.awt.Point;
import java.io.*;
import java.util.*;

public class Solution {

    static class Core {
        Point p;

        public Core(Point p) {
            this.p = p;
        }
    }

    static int N, maxCore, minWire;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] map;
    static ArrayList<Core> coreList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            coreList = new ArrayList<>();
            int temp = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    temp = Integer.parseInt(st.nextToken());
                    if (temp == 1) {
                        map[i][j] = 1;
                        if (i == 0 || j == 0 || i == N - 1 || j == N - 1) {
                            continue;
                        }
                        coreList.add(new Core(new Point(i, j)));
                    }
                }
            }

            maxCore = Integer.MIN_VALUE;
            minWire = Integer.MAX_VALUE;

            dfs(0, 0, 0);

            sb.append("#").append(tc).append(" ").append(minWire).append("\n");
        }

        System.out.println(sb);
    }

    public static void dfs(int idx, int coreCnt, int wireCnt) {
        if (idx == coreList.size()) {
            if (coreCnt > maxCore) {
                maxCore = coreCnt;
                minWire = wireCnt;
            } else if (coreCnt == maxCore) {
                minWire = Math.min(minWire, wireCnt);
            }
            return;
        }

        Core cur = coreList.get(idx);
        int cr = cur.p.x;
        int cc = cur.p.y;

        boolean isConnected = true;
        for (int i = 0; i < 4; i++) {
            int count = 0, nr = cr, nc = cc;
            isConnected = false;
            while (true) {
                nr += dr[i];
                nc += dc[i];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                    break;
                }
                if (map[nr][nc] == 1) {
                    count = 0;
                    break;
                }

                count++;
            }

            if (count > 0) {
                isConnected = true;

                int installWireRow = cr;
                int installWireCol = cc;

                for (int j = 0; j < count; j++) {
                    installWireRow += dr[i];
                    installWireCol += dc[i];
                    map[installWireRow][installWireCol] = 1;
                }

                dfs(idx + 1, coreCnt + 1, wireCnt + count);

                installWireRow = cr;
                installWireCol = cc;

                for (int j = 0; j < count; j++) {
                    installWireRow += dr[i];
                    installWireCol += dc[i];
                    map[installWireRow][installWireCol] = 0;
                }
            }
        }

        dfs(idx + 1, coreCnt, wireCnt);
        
    }
}
