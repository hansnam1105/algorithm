import java.io.*;
import java.util.*;

public class Main {
	
	static class Computer implements Comparable<Computer>{
		int from, to, cost;

		public Computer(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Computer c) {
			return Integer.compare(this.cost, c.cost); // 오름차순
		}
	}
	
	static int[] parents;
	static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		parents = new int[N];
		Arrays.fill(parents, -1);
		
		Computer[] comps = new Computer[M];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			comps[i] = new Computer(a-1, b-1, c);
		}
		
		Arrays.sort(comps);
		
		int cnt = 0, cost = 0;
		for(Computer comp : comps) {
			if(union(comp.from, comp.to)) {
				cost += comp.cost;
				if(++cnt == N-1) break;
			}
		}
		
		System.out.println(cost);
		
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
