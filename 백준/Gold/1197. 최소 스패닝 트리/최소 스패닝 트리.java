import java.io.*;
import java.util.*;

/**
 * 최소스패닝트리 공부
 */

public class Main {
	
	static int V, E;
	static int[] parent;
	static class Node implements Comparable<Node>{
		int s, e, weight;
		
		public Node(int s, int e, int weight) {
			this.s = s;
			this.e = e;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node e) {
			return this.weight -e.weight;
		}
	}
	
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		List<Node> list = new ArrayList<>();
		parent = new int[V+1];
		for(int i=1; i<=V; i++) {
			parent[i] = i;
		}
		
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list.add(new Node(s, e, w));
		}
		
		Collections.sort(list);
		
		int sum = 0;
		
		for(Node n : list) {
			int s = n.s;
			int e = n.e;
			int w = n.weight;
			
			if(findSet(s) == findSet(e))
				continue;
			
			sum += w;
			union(s, e);
		}
		
		bw.write(String.valueOf(sum));
		bw.flush();
		bw.close();
		
		
	}
	
	static int findSet(int a) {
		if(a == parent[a]) {
			return a;
		}
		return parent[a] = findSet(parent[a]);
	}
	
	static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot == bRoot)
			return;
		
		parent[bRoot] = aRoot;
		return;
	}
	
	
	
}
