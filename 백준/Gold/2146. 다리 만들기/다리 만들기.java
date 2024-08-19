import java.util.*;
import java.io.*;

public class Main {
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static boolean[][] visited;
	static int[][] map;
	static int N, minVal;
	
	static class isLand{
		int count;
		int y, x;
		
		public isLand(int y, int x, int count) {
			this.y = y;
			this.x = x;
			this.count = count;

		}
		
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		int iNum = 2;
		minVal = Integer.MAX_VALUE;
	
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		// 섬 탐색 및 섬마다 구분하게 2번부터 넘버링
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 1) {
					search(i,j, iNum++);
				}
			}
		}
		
		// 최소 거리 탐색
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]>1) {
					bfs(i,j, map[i][j]);
				}
			}
		}
		
		bw.write(String.valueOf(minVal-1));
		bw.flush();
		bw.close();
	}
	
	// 섬 판별 및 섬 넘버링
	public static void search(int y, int x, int num) {
		
		visited[y][x] = true;
		map[y][x] = num;
		for(int d=0; d<4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			if(ny <0 || ny>=N ||nx<0 || nx>=N) {
				continue;
			}
			if(visited[ny][nx] || map[ny][nx] != 1) {
				continue;
			}
			search(ny, nx, num);
		}
		
	}
	
	// 섬간 이동 다리 판별
	// 출발 좌표 & 시작 섬 코드 전송
	public static void bfs(int y, int x, int iCode) {
		Queue<isLand> q = new LinkedList<>();
		visited = new boolean[N][N];
		visited[y][x] = true;
		// q에서 0부터 탐색 시작
		q.add(new isLand(y, x, 0));
		while(!q.isEmpty()) {
			isLand cur = q.poll();
			int cy = cur.y;
			int cx = cur.x;
			int count = cur.count;
			// 새로운 섬을 만날 경우
			if(map[cy][cx] != 0 && map[cy][cx] != iCode) {
				minVal = Math.min(minVal, count);
				return;
			}
			// 섬 탐색
			for(int d=0; d<4; d++) {
				int ny = cy + dy[d];
				int nx = cx + dx[d];
				if(ny <0 || ny>=N ||nx<0 || nx>=N) {
					continue;
				}
				if(map[ny][nx] == iCode) {
					continue;
				}
				if(visited[ny][nx]) {
					continue;
				}
				q.add(new isLand(ny, nx, count+1));
				visited[ny][nx] = true;
			}
		}
	}

}
