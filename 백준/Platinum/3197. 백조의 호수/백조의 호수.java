import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {

    static int R, C;
    static char[][] map;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static boolean[][] visited;

    static Point start, end;
    static Queue<Point> Q;
    static Queue<Point> water;

    static boolean check() {
        Queue<Point> queue = new ArrayDeque<>();
        while(!Q.isEmpty()){
            int size = Q.size();
            while(size-- > 0){
                Point cur = Q.poll();
                int r = cur.x;
                int c = cur.y;

                if(r == end.x && c == end.y){
                    return true;
                }

                for(int i=0; i<4; i++){
                    int nr = r + dr[i];
                    int nc = c + dc[i];

                    if (inRange(nr, nc) || visited[nr][nc]) {
                        continue;
                    }

                    visited[nr][nc] = true;
                    if (map[nr][nc] == 'X') {
                        queue.add(new Point(nr, nc));
                        continue;
                    }
                    Q.add(new Point(nr, nc));
                }
            }
        }
        Q = queue;
        return false;
    }

    static boolean inRange(int r, int c){
        return r < 0 || c < 0 || r >= R || c >= C;
    }

    static void melt() {
        Queue<Point> melting = new ArrayDeque<>();

        while(!water.isEmpty()){
            Point cur = water.poll();
            int r = cur.x;
            int c = cur.y;


            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (inRange(nr, nc) || map[nr][nc] != 'X') {
                    continue;
                }

                map[nr][nc] = '.';
                melting.add(new Point(nr, nc));
            }
        }
        water = melting;
    }


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        visited = new boolean[R][C];
        map = new char[R][C];

        water = new ArrayDeque<>();
        Q = new ArrayDeque<>();

        for(int i=0; i<R; i++){
            map[i] = br.readLine().toCharArray();
            for(int j=0; j<C; j++){
                if(map[i][j] == 'L'){
                    if(start == null) {
                        start = new Point(i,j);
                        visited[i][j] = true;
                        Q.add(start);
                    } else {
                        end = new Point(i,j);
                    }
                    water.add(new Point(i,j));
                } else if(map[i][j] == '.'){
                    water.add(new Point(i,j));
                }
            }
        }

        int day = 0;

        while(true) {
            if(check()){
                bw.write(day + "\n");
                break;
            }

            melt();
            day++;
        }

        bw.flush();
        bw.close();




    }
}