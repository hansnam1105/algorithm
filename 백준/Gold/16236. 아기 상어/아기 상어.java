/**
 * P16236
 */

import java.util.*;
import java.io.*;
    
public class Main {

    static int N, minX, minY, minDist;
    static int[][] map;
    static int[][] dist;
    static int size = 2, eat, count;
    static int sharkX = -1, sharkY = -1;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };

    static class Shark {
        int x, y;

        Shark(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        int fCount = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int fish = Integer.parseInt(st.nextToken());
                if (fish > 0) {
                    map[i][j] = fish;
                    if (fish == 9) {
                        sharkX = j;
                        sharkY = i;
                        map[i][j] = 0;
                    }
                    fCount++;
                }
            }
        }
        if (fCount == 1) {
            System.out.println(0);
            return;
        }

        while (true) {
            dist = new int[N][N];
            minX = Integer.MAX_VALUE;
            minY = Integer.MAX_VALUE;
            minDist = Integer.MAX_VALUE;

            bfs(sharkY, sharkX);

            if (minX != Integer.MAX_VALUE && minY != Integer.MAX_VALUE) {
                eat++;
                map[minY][minX] = 0;
                sharkX = minX;
                sharkY = minY;
                count += dist[minY][minX];

                if (eat == size) {
                    eat = 0;
                    size++;
                }
            } else {
                break;
            }

        }
        System.out.println(count);

    }

    public static void bfs(int y, int x) {
        Queue<Shark> queue = new LinkedList<>();
        queue.add(new Shark(y, x));

        while (!queue.isEmpty()) {
            Shark s = queue.poll();
            int curX = s.x;
            int curY = s.y;

            for (int i = 0; i < 4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                    if (map[ny][nx] <= size && dist[ny][nx] == 0) {
                        dist[ny][nx] = dist[curY][curX] + 1;

                        if (map[ny][nx] != 0 && map[ny][nx] < size) {
                            if (minDist > dist[ny][nx]) {
                                minDist = dist[ny][nx];
                                minY = ny;
                                minX = nx;
                            } else if (minDist == dist[ny][nx]) {
                                // 거리가 같다면 더 위쪽, 더 왼쪽 물고기를 우선 선택
                                if (minY > ny || (minY == ny && minX > nx)) {
                                    minY = ny;
                                    minX = nx;
                                }
                            }
                        }

                        queue.add(new Shark(ny, nx));
                    }
                    
                }
            }
        }


        
    }
}