/**
 * P17472
 * 다리만들기2
 * 섬 - 상하좌우 - 사각형
 * 다리 길이 = A 섬 - B 섬 -1
 * 다리길이>=2 / 오로지 직진
 * 다리 교차시 각 칸 +1
 * 모든 섬 연결하는 다리 최솟값
 * 
 * 섬을 넘버링?
 * 각 섬 BFS를 통해 그룹화
 * DFS를 통해 모든 간선 즉 다리 구하기
 * 탐색을 위한 visited 배열
 * 크루스칼 MST 구현 - 다리 길이 최솟값 출력
 */

import java.util.*;
import java.io.*;
import static java.lang.Integer.parseInt;

public class Main {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = { 0, 0, -1, 1 };
    static ArrayList<int[]>[] list;
    
    // 크루스칼
    static int[] parents;
    static PriorityQueue<Edge> pq;
    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int length;

        public Edge(int from, int to, int length) {
            this.from = from;
            this.to = to;
            this.length = length;
        }

        @Override
        public int compareTo(Edge e) {
            return this.length - e.length;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = parseInt(st.nextToken());
            }
        }

        // BFS 탐색을 통해 섬 그룹화
        int land = 1;
        list = new ArrayList[7]; // 섬 최대 개수 6개
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    list[land] = new ArrayList<>();
                    searchIsland(i, j, land);
                    land++;
                }
            }
        }

        // 섬 최소 거리 탐색 - 유니온파인드 MST 크루스칼?
        pq = new PriorityQueue<>();
        for (int i = 1; i < land; i++) { // 섬마다
            for (int j = 0; j < list[i].size(); j++) { // 섬에 있는 점마다 다리 이으기
                // 다리 만들기 함수
                // 한방향만 탐색
                int[] island = list[i].get(j);
                for (int d = 0; d < 4; d++) {
                    // 탐색 방향, 시작 좌표, 섬id, 길이 전달
                    makeBridge(d, island[0], island[1], i, 0);
                }

            }
        }
        
        parents = new int[land];
        for (int i = 1; i < land; i++) {
            parents[i] = i;
        }

        int result = 0;
        int cnt = 0;
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (union(cur.from, cur.to)) {
                result += cur.length;
                // 섬의 개수는 land-1개 크루스칼 할시 ++cnt == N-1
                // 여기서 N -> land-1 즉 ++cnt == land-2
                if (++cnt == land - 2) {
                    break;
                }
            }

        }
        
        int totalEdges = land - 2; // 필요한 간선의 수 (섬의 개수 - 1)
        if (result == 0 || cnt != totalEdges) {
            bw.write("-1");
        } else {
            bw.write(String.valueOf(result));
        }

        bw.flush();
        bw.close();
        

        


    }
    // 섬 구분
    public static void searchIsland(int r, int c, int land) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { r, c });
        visited[r][c] = true;
        map[r][c] = land;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            list[land].add(new int[] { cur[0], cur[1] });
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if (check(nr, nc) && !visited[nr][nc] && map[nr][nc] == 1) {
                    visited[nr][nc] = true;
                    map[nr][nc] = land;
                    queue.offer(new int[] { nr, nc });
                }
            }
        }
    }
    
    public static boolean check(int r, int c) {
        return (r >= 0 && r < N && c >= 0 && c < M);
    }
    // 섬 구분 끝

    // 다리 연결 DFS
    public static void makeBridge(int dir, int r, int c, int land, int length) {
        int nr = r + dr[dir];
        int nc = c + dc[dir];

        if (!check(nr, nc)) {
            return; 
        }
        if (map[nr][nc] == land) {
            return;
        }

        if (map[nr][nc] != 0 && map[nr][nc] != land) {
            // 다른 섬을 만났을 때
            if (length >= 2) {
                pq.offer(new Edge(land, map[nr][nc], length));
            }
            return;
        }

        makeBridge(dir, nr, nc, land, length + 1);

    }
    


    public static int findSet(int a) {
        if (a == parents[a]) {
            return a;
        }

        return parents[a] = findSet(parents[a]);
    }

    public static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if (aRoot == bRoot)
            return false;

        parents[bRoot] = aRoot;
        return true;
    }
}