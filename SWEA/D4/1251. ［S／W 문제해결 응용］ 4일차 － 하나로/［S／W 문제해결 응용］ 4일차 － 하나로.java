/**
 * 1251 하나로
 * 그래프 문제이다
 * 일단 최소환경 부담금 으로 섬들을 연결해야하는 문제이기에
 * MST 최소 신장 트리 문제이다
 * 크루스칼 알고리즘으로 접근하려고한다
 * 또한 이 문제의 경우 X,Y 좌표 만 주어지기에 가중치는 섬 끼리의 길이를 타겟으로함
 * 우선 모든 섬의 간선을 만든다
 * 이를 PQ에 담아 가장 가중치가 낮은 간선으로 정렬에
 * 가장 짧은 해도터널을 만든다
 */

import java.io.*;
import java.util.*;


public class Solution {
	
	static class Edge implements Comparable<Edge>{
		int start, end; 
		long cost;

		public Edge(int start, int end, long cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.cost, o.cost);
		}
		
		
	}
	
	static long[] X, Y;
	static int[] parents;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer x, y;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			
			int N = Integer.parseInt(br.readLine());
			
			x = new StringTokenizer(br.readLine());
			y = new StringTokenizer(br.readLine());

			X = new long[N]; Y = new long[N];
			for(int i=0; i<N; i++) {
				X[i] = Long.parseLong(x.nextToken());
				Y[i] = Long.parseLong(y.nextToken());
			}
			
			double E = Double.parseDouble(br.readLine());

			// make
			parents = new int[N];
			Arrays.fill(parents, -1);
			
			// PQ를 통해 모든 간선 가중치 낮은 순으로 정렬
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			
			long dist = 0;
			// 가능한 모든 간선 생성
			for(int i=0; i<N; i++) {
				for(int j=i+1; j<N; j++) {
					dist = (X[i]-X[j])*(X[i]-X[j]) + (Y[i]-Y[j])*(Y[i]-Y[j]); // L^2
					pq.add(new Edge(i,j,dist));
				}
			}
			
			int cnt = 0;
			long cost = 0;
			while(!pq.isEmpty()) {
				Edge edge = pq.poll();
				if(union(edge.start, edge.end)) {
					cost += edge.cost;
					if(++cnt == N-1) break;
				}
			}
			
			double result = cost * E;
			
			sb.append("#").append(tc).append(" ").append(Math.round(result)).append("\n");
			
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
		
		parents[aRoot] += parents[bRoot];
		parents[bRoot] = aRoot;
		
		return true;
	}

}
