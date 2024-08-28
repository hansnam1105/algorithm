import java.io.*;
import java.util.*;

public class Solution {
	
	static int[] parents;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			// make
			parents = new int[N+1];
			for(int i=1; i<=N; i++) {
				parents[i] = i;
			}
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a, b);
				
			}
			Set<Integer> uniqueGroups = new HashSet<>();
            for(int i=1; i<=N; i++) {
                uniqueGroups.add(findSet(i));
            }
            int result = uniqueGroups.size();
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
			
		}
		
		System.out.println(sb);
		
		

	}
	
	public static int findSet(int a) {
		if(a == parents[a]) {
			return a;
		}
		else {
			return parents[a] = findSet(parents[a]);
		}
		
	}
	
	public static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot == bRoot) return;
		
		parents[bRoot] = aRoot;
		return;
	}

}
