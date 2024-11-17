import java.io.*;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge> {
        int start, end, weight;

        Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    static int[] parent, rank;
    static ArrayList<Edge> edgeList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        edgeList = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edgeList.add(new Edge(start, end, weight));
        }

        parent = new int[N + 1];
        rank = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        Collections.sort(edgeList);

        int totalWeight = 0;
        int maxWeightInMST = 0;
        int edgeCount = 0;
        for (Edge edge : edgeList) {
            if (findRoot(edge.start) != findRoot(edge.end)) {
                totalWeight += edge.weight;
                unionSets(edge.start, edge.end);
                maxWeightInMST = edge.weight;
                edgeCount++;

                if (edgeCount == N - 1) {
                    break;
                }
            }
        }

        bw.write((totalWeight - maxWeightInMST) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static int findRoot(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = findRoot(parent[x]);
    }

    public static void unionSets(int x, int y) {
        x = findRoot(x);
        y = findRoot(y);

        if (x != y) {
            if (rank[x] > rank[y]) {
                parent[y] = x;
            } else if (rank[x] < rank[y]) {
                parent[x] = y;
            } else {
                parent[y] = x;
                rank[x]++;
            }
        }
    }
}
