import java.io.*;
import java.util.*;

public class Main {
    
    static class Node implements Comparable<Node> {
        int end;
        int weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }
    }

    static int N, M;
    static ArrayList<ArrayList<Node>> adj; // 인접리스트.
    static int[] dist; // 시작점에서 각 정점으로 가는 최단거리.
    static boolean[] check; // 방문 확인
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        
        adj = new ArrayList<>();
        dist = new int[N + 1];
        check = new boolean[N + 1];
        
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }

        // 단방향 인접 리스트 구현.
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            // start에서 end로 가는 weight (가중치)
            adj.get(start).add(new Node(end, weight));
        }
        
        st = new StringTokenizer(br.readLine());
        int startPos = Integer.parseInt(st.nextToken());
        int endPos = Integer.parseInt(st.nextToken());
        
        bw.write(dijkstra(startPos, endPos) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
    
    public static int dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] check = new boolean[N + 1];
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int cur = curNode.end;

            if (!check[cur]) {
                check[cur] = true;

                for (Node node : adj.get(cur)) {  // Corrected from 'a' to 'adj'
                    if (!check[node.end] && dist[node.end] > dist[cur] + node.weight) {
                        dist[node.end] = dist[cur] + node.weight;
                        pq.add(new Node(node.end, dist[node.end]));
                    }
                }
            }
        }
        return dist[end];
    }
}
