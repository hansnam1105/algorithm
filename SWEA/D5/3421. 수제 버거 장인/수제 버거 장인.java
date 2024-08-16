import java.util.*;
import java.io.*;


public class Solution {

		static int N, M;
		static int[] burger;
		static int ans;
		static boolean[][] noMatch;
		
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			noMatch = new boolean[400][400];
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				noMatch[a][b] = noMatch[b][a] = true;
				
			}
			
			ans = 0;
			burger = new int[N];
			boolean[] visited = new boolean[N];
			burgerComb(0, 0, visited);
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
			
		}
		
		System.out.println(sb);

	}
	
	public static void burgerComb(int startIdx, int depth, boolean[] visited) {
		
		ans++;
		
		for(int i=startIdx; i<N; i++) {
			if(visited[i]) {
				continue;
			}
			if(isPossible(i+1, depth)) {
				continue;
			}
			visited[i] = true;
			burger[depth] = i+1;
			burgerComb(i+1, depth+1, visited);
			visited[i] = false;
			burger[depth] = 0;
			
		}
	}
	
	public static boolean isPossible(int mat, int depth) {
		for(int j=0; j<depth; j++) {
			if(noMatch[mat][burger[j]]) {
				return true;
			}
		}
		return false;
	}

}
