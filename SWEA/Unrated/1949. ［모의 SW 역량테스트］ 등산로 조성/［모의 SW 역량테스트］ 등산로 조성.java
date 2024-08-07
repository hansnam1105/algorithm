import java.util.*;
import java.io.*;

public class Solution {
	
	static int N, K, maxHeight, maxRoute;
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	static StringBuilder sb = new StringBuilder();
	static int[][] mountain;
	static boolean[][] visited;
	
	class Top{
		int x, y;
		
		public Top(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		int tempK = K;

		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			maxHeight = 1;
			maxRoute = 0;
			mountain = new int[N][N];
			visited = new boolean[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					mountain[i][j] = Integer.parseInt(st.nextToken());
					maxHeight = Math.max(maxHeight, mountain[i][j]);
				}
			}
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(mountain[i][j] == maxHeight) {
						visited[i][j] = true;
						dfs(i, j, 1, 1);
						visited[i][j] = false;
					}
				}
			}
			sb.append("#" + tc + " " + maxRoute + "\n");
		}
		System.out.println(sb);
		
	}
	
	public static void dfs(int y, int x, int count, int kAvl ) {
		int curLoc = mountain[y][x];
		
		for(int i=0; i<4; i++) {
			if(y + dy[i] < 0 || y+dy[i] >=N || x+dx[i] <0 || x+dx[i] >=N ) {
				continue;
			}
			int nextLoc = mountain[y+dy[i]][x+dx[i]];
			if(!visited[y+dy[i]][x+dx[i]] && nextLoc < curLoc) {
				visited[y+dy[i]][x+dx[i]] = true;
				dfs(y+dy[i], x+dx[i], count+1, kAvl);
				visited[y+dy[i]][x+dx[i]] = false;
			}
			else if(kAvl>0) {
				for(int j=1; j<=K; j++) {
					if(!visited[y+dy[i]][x+dx[i]] && nextLoc-j < curLoc ) {
						mountain[y+dy[i]][x+dx[i]] -=j;
						dfs(y+dy[i], x+dx[i], count+1, kAvl-1);
						mountain[y+dy[i]][x+dx[i]] +=j;
					}
				}
			}
			
		}
		if(count>maxRoute) {
			maxRoute = count;
		}
	}
}