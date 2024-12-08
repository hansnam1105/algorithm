import java.io.*;
import java.util.*;

public class Main {
	
	static class Edge implements Comparable<Edge> {
	    int from, to, cost;

	    public Edge(int from, int to, int cost) {
	        this.from = from;
	        this.to = to;
	        this.cost = cost;
	    }

	    @Override
	    public int compareTo(Edge other) {
	        return this.cost - other.cost;
	    }
	}

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        int[][] positions = new int[n][4]; // x, y, z, index

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            positions[i][0] = Integer.parseInt(input[0]); // x
            positions[i][1] = Integer.parseInt(input[1]); // y
            positions[i][2] = Integer.parseInt(input[2]); // z
            positions[i][3] = i; // index
        }

        List<Edge> edges = new ArrayList<>();

        for (int d = 0; d < 3; d++) {
        	int dim = d;
            Arrays.sort(positions, Comparator.comparingInt(a -> a[dim]));
            for (int i = 0; i < n - 1; i++) {
                int cost = Math.abs(positions[i][dim] - positions[i + 1][dim]);
                edges.add(new Edge(positions[i][3], positions[i + 1][3], cost));
            }
        }

        Collections.sort(edges);

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int mstCost = 0;
        int edgeCount = 0;

        for (Edge edge : edges) {
            if (union(edge.from, edge.to)) {
                mstCost += edge.cost;
                edgeCount++;
                if (edgeCount == n - 1) break; // MST 완성
            }
        }

        bw.write(mstCost + "\n");
        bw.flush();
    }

    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            parent[rootB] = rootA;
            return true;
        }
        return false;
    }
}
