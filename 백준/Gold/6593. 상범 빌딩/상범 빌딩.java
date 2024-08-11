import java.util.*;
import java.io.*;

public class Main {

    static int L, R, C;
    static char[][][] building;
    static int[][][] dist;
    /*
     * L = 빌딩 층 수
     * R = 한층의 행
     * C = 한층의 열
     */
    static int[] dx = { -1, 1, 0, 0, 0, 0 };
    static int[] dy = { 0, 0, -1, 1, 0, 0 };
    static int[] dz = { 0, 0, 0, 0, 1, -1 };
    static StringBuilder sb = new StringBuilder();
    //금으로 막혀있어 지나갈 수 없는 칸은 '#'으로 표현되고, 비어있는 칸은 '.'
    static class Loc {
        int x, y, z;

        Loc(int z, int y, int x) {
            this.z = z;
            this.y = y;
            this.x = x;
        }
    }
    
    static Loc S, E;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        if (L == 0 && R == 0 && C == 0) {
            return;
        }

        building = new char[L][R][C];
        dist = new int[L][R][C];
        for (int f = 0; f < L; f++) {
            for (int i = 0; i < R; i++) {
                String line = br.readLine();
                for (int j = 0; j < C; j++) {
                    building[f][i][j] = line.charAt(j);
                    if (building[f][i][j] == 'S') {
                        S = new Loc(f, i, j);
                        building[f][i][j] = '.';
                    }
                    if (building[f][i][j] == 'E') {
                        E = new Loc(f, i, j);
                    }
                }
            }
            br.readLine();
        }
        dist[S.z][S.y][S.x] = 1;

        bfs(S);

        System.out.println(sb);
        sb = new StringBuilder();   
        }
        

    }
    
    public static void bfs(Loc current) {
        Queue<Loc> queue = new LinkedList<>();
        queue.offer(current);


        while (!queue.isEmpty()) {
            Loc cur = queue.poll();
            int cx = cur.x;
            int cy = cur.y;
            int cz = cur.z;
            if (building[cz][cy][cx] == 'E') {
                break;
            }

            for (int i = 0; i < 6; i++) {
                int nz = cz + dz[i];
                int ny = cy + dy[i];
                int nx = cx + dx[i];

                if (nz < 0 || ny < 0 || nx < 0 || nz >= L || ny >= R || nx >= C ||
                        building[nz][ny][nx] == '#' || dist[nz][ny][nx] != 0) {
                    continue;
                }
                dist[nz][ny][nx] = dist[cz][cy][cx] + 1;
                queue.offer(new Loc(nz, ny, nx));

            }
        }
        
        if (dist[E.z][E.y][E.x] != 0) {
            int distance = dist[E.z][E.y][E.x] - 1;
            sb.append("Escaped in " + distance + " minute(s).");
            return;
        }
        else {
            sb.append("Trapped!");
        }

        
    }
}
