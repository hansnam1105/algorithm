import java.io.*;
import java.util.*;

// 백준 1162 도로포장

public class Main {

    static int N, M, K;
    static List<Node>[] graph;
    static long[][] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, cost));
            graph[v].add(new Node(u, cost));
        }

        dist = new long[N + 1][K + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }

        dijkstra();

        long result = Long.MAX_VALUE;
        for (int k = 0; k <= K; k++) {
            result = Math.min(result, dist[N][k]);
        }

        System.out.println(result);
    }

    static void dijkstra() {
        PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> Long.compare(a.cost, b.cost));
        pq.add(new State(1, 0, 0));
        dist[1][0] = 0;

        while (!pq.isEmpty()) {
            State curr = pq.poll();

            if (curr.cost > dist[curr.node][curr.paved]) {
                continue;
            }

            for (Node next : graph[curr.node]) {
                // 도로를 포장하지 않는 경우
                if (dist[next.to][curr.paved] > curr.cost + next.cost) {
                    dist[next.to][curr.paved] = curr.cost + next.cost;
                    pq.add(new State(next.to, curr.paved, dist[next.to][curr.paved]));
                }

                // 도로를 포장하는 경우
                if (curr.paved < K && dist[next.to][curr.paved + 1] > curr.cost) {
                    dist[next.to][curr.paved + 1] = curr.cost;
                    pq.add(new State(next.to, curr.paved + 1, dist[next.to][curr.paved + 1]));
                }
            }
        }
    }

    static class Node {
        int to;
        int cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static class State {
        int node;
        int paved;
        long cost;

        public State(int node, int paved, long cost) {
            this.node = node;
            this.paved = paved;
            this.cost = cost;
        }
    }
}
