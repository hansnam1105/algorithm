/**
 * P7569
 */
import java.util.*;
import java.io.*;

public class Main {
    static class Tomato{
        int z;
        int y;
        int x;

        public Tomato(int z, int x, int y){
            this.z = z;
            this.x = x;
            this.y = y;
        }
    }

    static int M, N ,H;
    static int[][][] box;
    static int[] dz = {0 ,0 ,0, 0, -1, 1};
    static int[] dx = {-1, 0, 1, 0, 0, 0};
    static int[] dy = {0, 1, 0, -1, 0, 0};
    static int result = Integer.MIN_VALUE;



    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        box = new int[H][N][M];
        List<Tomato> list = new ArrayList<>();


        for(int i=0; i<H; i++){
            for(int j=0; j<N; j++){
                st = new StringTokenizer(br.readLine());
                for(int k=0; k<M; k++){
                    box[i][j][k] = Integer.parseInt(st.nextToken());
                    if(box[i][j][k] == 1){
                        list.add(new Tomato(i, j, k));
                    }
                }
            }
        }

        System.out.println(bfs(list));
        
    }

    public static int bfs(List<Tomato> list){
        Queue<Tomato> q = new LinkedList<>();
        for(int i=0; i<list.size(); i++){
            q.offer(list.get(i));
        }

        while(!q.isEmpty()){
            Tomato cur = q.poll();

            for(int i=0; i<6; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                int nz = cur.z + dz[i];

                if(nx<0 || nx>=N || ny<0 || ny>=M || nz<0 || nz>=H){
                    continue;
                }
                if (box[nz][nx][ny] != 0) {
                    continue;
                }
                q.offer(new Tomato(nz, nx, ny));

                box[nz][nx][ny] = box[cur.z][cur.x][cur.y] + 1;

            }

        }
        int result = Integer.MIN_VALUE;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (box[i][j][k] == 0) return -1;
                    result = Math.max(result, box[i][j][k]);
                }
            }
        }

        return result == 1 ? 0 : result - 1;


    }
}