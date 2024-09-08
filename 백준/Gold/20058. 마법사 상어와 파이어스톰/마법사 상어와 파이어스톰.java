/**
 * 마법사 상어와 파이어스톰
 * 2^N x 2^N 얼음판
 * 파이어스톰 시전 magicArr의 배열 값 가져와서
 * 2^L x 2^L의 크기 부분격자
 * 모든 부분 격자 시계 방향 90도 회전
 * 비트마스킹?
 * 2^N -> 1 << N
 * 
 * 격자 생성 후 rotate 함수
 * 얼음이 있는 칸 3개 또는 그 이상과 인접해있지 않은 칸은 얼음 -1
 * 위를 위한 dr dc
 * 얼음 덩어리 탐색을 위한 bfs
 * return A[r][c]의 모든 합
 * return 덩어리 차지 칸의 개수
 */

import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {

    static int N, Q;
    static int[] magicArr;
    static int[][] map;
    static boolean[][] visited;

    // 동남서북 3시부터 12시로 탐색
    static int[] dr = { 0, 1, 0, -1 };
    static int[] dc = { 1, 0, -1, 0 };

    // return 값
    static int remainedIce, maxVal;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 입력
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        map = new int[1 << N][1 << N];
        magicArr = new int[Q];

        for (int i = 0; i < (1 << N); i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < (1 << N); j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            magicArr[i] = Integer.parseInt(st.nextToken());
        }

        remainedIce = 0;
        maxVal = 0;

        // 파이어스톰 시전
        fireStorm();

        bw.write(String.valueOf(remainedIce));
        bw.newLine();
        bw.write(String.valueOf(maxVal));
        bw.flush();
        bw.close();
    }
    
    // 파이어스톰
    static void fireStorm() {

        // 격자 회전 & 얼음 녹이기
        for (int i = 0; i < Q; i++) {
            rotate(magicArr[i]);
        }

        // 남아있는 얼음 A[r][c]의 합
        for (int i = 0; i < (1 << N); i++) {
            for (int j = 0; j < (1 << N); j++) {
                remainedIce += map[i][j];
            }
        }

        // 남아있는 얼음 중 가장 큰 덩어리가 차지하는 칸의 개수
        visited = new boolean[1 << N][1 << N];
        for (int i = 0; i < (1 << N); i++) {
            for (int j = 0; j < (1 << N); j++) {
                if (map[i][j] == 0 || visited[i][j]) {
                    continue;
                }
                maxVal = Math.max(maxVal, bfs(i, j));
            }
        }
    }
    
    // 격자 회전
    static void rotate(int len) {
    int[][] rotateMap = new int[1 << len][1 << len];

    for (int i = 0; i < (1 << N); i += (1 << len)) {
        for (int j = 0; j < (1 << N); j += (1 << len)) {
            // 90도 회전을 위해 기존 값 복사
            for (int r = i; r < i + (1 << len); r++) {
                for (int c = j; c < j + (1 << len); c++) {
                    rotateMap[c - j][(1 << len) - 1 - (r - i)] = map[r][c];
                }
            }

            // 회전된 값을 다시 원래 map에 저장
            for (int r = i; r < i + (1 << len); r++) {
                for (int c = j; c < j + (1 << len); c++) {
                    map[r][c] = rotateMap[r - i][c - j];
                }
            }
        }
    }

    meltIce();
}

    
    // 얼음 줄이기
    static void meltIce() {
        List<Point> meltPoints = new ArrayList<>();

        for (int i = 0; i < (1 << N); i++) {
            for (int j = 0; j < (1 << N); j++) {
                if (map[i][j] == 0)
                    continue;
                int adjCount = 0; // 인접 값
                // 인접 칸 체크
                for (int d = 0; d < 4; d++) {
                    int nr = i + dr[d];
                    int nc = j + dc[d];
                    if (isRange(nr, nc) && map[nr][nc] > 0) {
                        adjCount++;
                    }
                }
                if (adjCount < 3) {
                    meltPoints.add(new Point(i, j));
                }
            }
        }

        for (Point p : meltPoints) {
            map[p.x][p.y]--;
        }
    }

    static boolean isRange(int r, int c) {
        return (r >= 0 && r < (1 << N) && c >= 0 && c < (1 << N));
    }
    
    // 큰 덩어리 찾기 BFS
    static int bfs(int r, int c) {
        Queue<Point> q = new LinkedList<>();
        visited[r][c] = true;
        q.offer(new Point(r, c));
        int cell = 1;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur.x + dr[d];
                int nc = cur.y + dc[d];
                if (isRange(nr, nc) && !visited[nr][nc] && map[nr][nc] != 0) {
                    visited[nr][nc] = true;
                    q.offer(new Point(nr, nc));
                    cell++;
                }
            }
        }
        return cell;
    }
}
