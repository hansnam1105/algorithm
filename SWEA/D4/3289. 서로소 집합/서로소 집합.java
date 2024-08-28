import java.util.*;
import java.io.*;


public class Solution {

	static int N, M;
	
	static int[] parents;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			parents = new int[N+1];
			
			//make
			for(int i=1; i<=N; i++) {
				parents[i] = i;
			}
			
			sb.append("#").append(tc).append(" ");
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int option = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				switch(option) {
					case 0 : 
						Union(a, b);
						break;
					case 1 :
						if(findSet(a) == findSet(b)) {
							sb.append("1");
						}
						else {
							sb.append("0");
						}
				}
			}
			
			sb.append("\n");
			
		}
		System.out.println(sb);

	}
	
	public static int findSet(int a) {
		if(a == parents[a])
			return a;
		else {
			return parents[a] = findSet(parents[a]);
		}
	}
	
	public static void Union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot == bRoot) return;
		
		parents[bRoot] = aRoot; 
		return;
	}

}
