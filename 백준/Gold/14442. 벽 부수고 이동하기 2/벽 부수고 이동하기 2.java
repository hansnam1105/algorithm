import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M, K, minRoute;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] map;
	static boolean[][][] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M][K+1];
		for(int i=0; i<N; i++) {
			String temp = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = temp.charAt(j) - '0';
			}
		}
		minRoute = -1;
		bfs();
		bw.write(String.valueOf(minRoute));
		bw.flush();
		bw.close();
		
	}
	
	static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {0,0,1,0});
		visited[0][0][0] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int curR = cur[0];
			int curC = cur[1];
			int dist = cur[2];
			int cK = cur[3];
			
			if(curR == N-1 && curC == M-1) {
				minRoute = dist;
				return;
			}
			
			for(int i=0; i<4; i++) {
				int nr = curR + dr[i];
				int nc = curC + dc[i];
				
				if(nr<0 || nr>=N || nc<0 || nc>=M) continue;
				if(map[nr][nc] == 0 && !visited[nr][nc][cK]) {
					visited[nr][nc][cK] = true;
					q.add(new int[] {nr, nc, dist+1, cK});
				} else {
					if(cK<K && !visited[nr][nc][cK+1]) {
						visited[nr][nc][cK+1] = true;
						q.add(new int[] {nr, nc, dist+1, cK+1});
					}
				}
			}		
		}
	}
	

}
