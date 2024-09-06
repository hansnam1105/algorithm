import java.io.*;
import java.util.*;

public class Solution {

	static int V, E, c1, c2, depth;
	static int[] parents;
	static ArrayList<Integer>[] graph;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			c1 = Integer.parseInt(st.nextToken());
			c2 = Integer.parseInt(st.nextToken());

			parents = new int[V + 1];
			graph = new ArrayList[V + 1];

			for (int i = 1; i <= V; i++) {
				graph[i] = new ArrayList<>();
			}

			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < E; i++) {
				int parent = Integer.parseInt(st.nextToken());
				int child = Integer.parseInt(st.nextToken());

				graph[parent].add(child);

				parents[child] = parent; //parents에 가장 마지막 부모 자식 관계 들어감
			}

			int ansNode = findLCA(c1, c2);;
			depth = findChild(ansNode);

			bw.write("#" + tc + " " + ansNode + " " + depth);
			bw.newLine();

		}
		bw.flush();
		bw.close();

	}
	
	static int findLCA(int node1, int node2) {
        HashSet<Integer> parentSet = new HashSet<>();
        
        while (node1 != 0) {
            parentSet.add(node1);
            node1 = parents[node1];
        }

        while (node2 != 0) {
            if (parentSet.contains(node2)) {
                return node2;
            }
            node2 = parents[node2];
        }
        
        return 0;
    }


	
	static int findChild(int node) {
		int count = 1; 
		for (int child : graph[node]) {
			count += findChild(child);
		}
		return count;

	}


	
}
