/**
 * 보물섬
 * 두 보물의 위치의 최단 거리를 구하라 하지만
 * 두 보물의 위치가 서로 육지로 이동하는데 가장 긴 시간이 걸리는곳
 * 결국 가장 육지상 먼곳 bfs 너비 우선 탐색
 */

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main {
	
	static char[][] map;
	static boolean[][] visited;
	static int row, column, maxVal;
	
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		row = parseInt(st.nextToken());
		column = parseInt(st.nextToken());
		
		map = new char[row][column];
		visited = new boolean[row][column];
		for(int i=0; i<row; i++) {
			String line = br.readLine();
			for(int j=0; j<column; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		maxVal = 0;
		//L 땅 하나 선택후 가장 거리 먼 땅 탐색 후 max값 갱신
		for(int i=0; i<row; i++) {
			for(int j=0; j<column; j++) {
				if(map[i][j] == 'L') {
					//매번 첫 보물 위치 선정시 방문 boolean 초기화
					visited = new boolean[row][column];
					bfs(i, j);
				}
			}
		}
		
		bw.write(String.valueOf(maxVal));
		bw.flush();
		bw.close();
		br.close();
		
		
	}
	
	public static void bfs(int y, int x) {
		Queue<Integer[]> q = new LinkedList<>();
		visited[y][x] = true;
		q.add(new Integer[]{y, x, 0}); //좌표, 이동거리
		// 너비 우선 탐색을 통해 계속 최대 이동거리 탐색
		while(!q.isEmpty()) {
			Integer[] cur = q.poll();
			int cury = cur[0];
			int curx = cur[1];
			int count = cur[2];
			// bfs
			for(int d=0; d<4; d++) {
				int ny = cury + dy[d];
				int nx = curx + dx[d];
				if(check(ny, nx)) {
					visited[ny][nx] = true;
					q.add(new Integer[] {ny, nx, count+1});
					maxVal = Math.max(maxVal, count+1);
				}
			}
		}
	}
	
	public static boolean check(int y, int x) {
		if(y<0 || y>=row || x<0 || x>=column) {
			return false;
		}
		else if(visited[y][x] == true) {
			return false;
		}
		else if(map[y][x] != 'L') {
			return false;
		}
		return true;
	}

}
