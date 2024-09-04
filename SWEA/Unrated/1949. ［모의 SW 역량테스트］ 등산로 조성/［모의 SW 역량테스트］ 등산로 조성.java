import java.io.*;
import java.util.*;

public class Solution {

	static int N, K, maxRoute;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			
			int maxHeight = 1;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					maxHeight = Math.max(maxHeight, map[i][j]);
				}
			}
			
			maxRoute = Integer.MIN_VALUE;
			visited = new boolean[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] == maxHeight) {
						visited[i][j] = true;
						dfs(1, i, j, 1);
						visited[i][j] = false;
					}
				}
			}
			
			bw.write("#"+tc+" "+maxRoute);
			bw.newLine();
			
			
		}
		bw.flush();
		bw.close();
		br.close();

	}
	
	public static void dfs(int depth, int r, int c, int kUsed) {
		int curLoc = map[r][c];
		for(int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(check(nr, nc)) {
				if(map[nr][nc] < curLoc) {
					visited[nr][nc] = true;
					dfs(depth+1, nr, nc, kUsed);
					visited[nr][nc] = false;
				} 
				else if(kUsed>0) {
					for(int k=1; k<=K; k++) {
						if(map[nr][nc] - k < curLoc) {
							map[nr][nc] -=k;
							dfs(depth+1, nr, nc, kUsed-1);
							map[nr][nc] +=k;
						}
					}
				}
					
			}	
		}
		
		if(depth>maxRoute) {
			maxRoute = depth;
			return;
		}
		
	}
	
	static boolean check(int r, int c) {
		return (r>=0 && r<N && c>=0 && c<N && !visited[r][c]);
	}

}
