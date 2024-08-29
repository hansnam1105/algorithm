import java.io.*;
import java.util.*;

public class Solution {

	static int V, E;
	static int[] parents;
	
	static class Edge implements Comparable<Edge>{
		int start, end, weight;

		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
		
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			// make
			parents = new int[V];
			for(int i=0; i<V; i++) {
				parents[i] = -1;
			}
			
			Edge[] edges = new Edge[E];
			
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				int C = Integer.parseInt(st.nextToken());
				
				edges[i] = new Edge(A-1, B-1, C);
			}
			
			Arrays.sort(edges);
			
			long cnt = 0, cost = 0;
			for(Edge edge : edges) {
				if(union(edge.start, edge.end)) {
					cost += edge.weight;
					if(++cnt == V-1) break; // 최소 신장 트리 완성!!
				}
			}
			sb.append("#").append(tc).append(" ").append(cost).append("\n");
			
		}
		
		System.out.println(sb);
	}
	
	public static int findSet(int a) {
		if(parents[a] < 0) {
			return a;
		}
		
		return parents[a] = findSet(parents[a]);
		
	}
	
	public static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot) {
			return false;
		}
		
		// 편의상 a 집합에 b 집합을 붙임 (집합의 크기에 따라 붙이도록 처리도 가능!)
		parents[aRoot] += parents[bRoot]; // 집합 크기 관리
		parents[bRoot] = aRoot;
		return true;
	}

}
