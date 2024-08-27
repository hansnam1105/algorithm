/**
 * 디저트 카페
 */

import java.io.*;
import java.util.*;

public class Solution {

	static int N, maxSize, startR, startC;
	// 탐색 순서
	static int[] dr = {1, 1, -1, -1};
	static int[] dc = {1, -1, -1, 1};
	
	static int[][] map;
	static boolean[][] visited;
	static HashSet<Integer> dessert;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			maxSize = -1;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					visited = new boolean[N][N];
					dessert = new HashSet<>();		
					startR = i;
					startC = j;
					dfs(0, i, j, 0, 0);
				}
			}
			
			sb.append("#").append(tc).append(" ").append(maxSize).append("\n");
		}
		System.out.println(sb);

	}
	
	public static void dfs(int depth, int r, int c, int dir, int turnCount) {
		if(depth>0 && r == startR && c == startC && turnCount >= 3) {
			maxSize = Math.max(maxSize, depth);
			return;
		}
		
		for(int i=dir; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(check(nr, nc) && !dessert.contains(map[nr][nc])) {
				visited[nr][nc] = true;
				dessert.add(map[nr][nc]);
				dfs(depth+1, nr, nc, i, turnCount + (dir!=i ? 1 : 0));
				dessert.remove(map[nr][nc]);
				visited[nr][nc] = false;
			}
			
		}
	}
	
	public static boolean check(int r, int c) {
        if (r >= 0 && c >= 0 && r < N && c < N && !visited[r][c]) {
            return true;
        }
        return false;
    }

}
