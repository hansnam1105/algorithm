import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M, minZone;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static char[][] map;
	static int[][] state; // 0 방문 x, 1 방문 중, 2 방문 완료
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		state = new int[N][M];
		
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		minZone = 0;

		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(state[i][j] == 0) {
					dfs(i,j);
				}
			}
		}
		
		bw.write(String.valueOf(minZone));
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static void dfs(int r, int c) {
		state[r][c] = 1;
		int dir = -1;
		switch(map[r][c]) {
		case 'U' :
			dir = 0;
			break;
		case 'D' :
			dir = 1;
			break;
		case 'L' :
			dir = 2;
			break;
		case 'R' :
			dir = 3;
			break;
		}
		
		int nr = r + dr[dir];
		int nc = c + dc[dir];
		
		if(state[nr][nc] == 0) { // 다음 위치 방문 하지 않으면
			dfs(nr, nc);
		} else if(state[nr][nc] == 1) { // 다음 위치 방문 중이면 사이클이다
			minZone++;
		}
		
		state[r][c] = 2; // 현재 위치 방문 완료
		
	}
	

}
