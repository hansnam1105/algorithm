/**
 * 방향 그래프 주어지면 시작점에서 다른 모든 정점으로의 최단 경로
 * 정점 V 간선 E 1~V 까지 번호
 * 시작 정점 번호 K
 * from u to v 가중치 w
 * 다익스트라 알고리즘
 *
 *
 */

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main {

	static class Node {
		int index, dist;
		
		public Node(int index, int dist) {
			this.index = index;
			this.dist = dist;
		}
	}
	
	static class Element implements Comparable<Element>{
		int dist, index;
		
		public Element(int dist, int index) {
			this.dist = dist;
			this.index = index;
		}
		
		@Override
		public int compareTo(Element e) {
			return this.dist - e.dist;
		}
	}
	
	public static ArrayList<Node>[] graph;
	public static int[] dist;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 정점의 수 / 간선의 수 / 시작 점
		int V = parseInt(st.nextToken()); 
		int E = parseInt(st.nextToken());
		int K = parseInt(br.readLine());
		
		graph = new ArrayList[V+1];
		for(int i=1; i<=V; i++) {
			graph[i] = new ArrayList<>();
		}
		
		// 그래프 인접리스트 표현
		for(int i=1; i<=E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = parseInt(st.nextToken());
			int v = parseInt(st.nextToken());
			int w = parseInt(st.nextToken());
			
			graph[u].add(new Node(v,w));
		}
		
		dist = new int[V+1];
		for(int i=1; i<=V; i++) {
			dist[i] = (int)1e9;
		}
		
		dist[K] = 0;
		
		PriorityQueue<Element> pq = new PriorityQueue<>();
		pq.offer(new Element(0, K));

		while(!pq.isEmpty()) {
			Element cur = pq.poll();
			int minDist = cur.dist;
			int minIndex = cur.index;
			
			// 같은 정점의 원소가 여러번 들어갈 수 있어
			// minDist 가 최신 dist[minIndex] 값과 다르면 패스
			if(minDist != dist[minIndex]) {
				continue;
			}
			
			for(int j=0; j<graph[minIndex].size(); j++) {
				int targetIndex = graph[minIndex].get(j).index;
				int targetDist = graph[minIndex].get(j).dist;
				
				int newDist = dist[minIndex] + targetDist;
				if(dist[targetIndex] > newDist) {
					dist[targetIndex] = newDist;
					pq.add(new Element(newDist, targetIndex));
				}
			}
		}
		
		for(int i=1; i<=V; i++) {
			if(dist[i] != (int)1e9)
				bw.write(String.valueOf(dist[i]));
			else
				bw.write("INF");
			bw.append("\n");
		}
		bw.flush();
		bw.close();
	}

}
