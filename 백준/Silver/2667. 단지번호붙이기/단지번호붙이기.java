import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] map;
	static boolean[][] visited;
	static List<Integer> result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			String temp = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = temp.charAt(j) - '0';
			}
		}
		
		result = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					bfs(i, j);
				}
			}
		}
		
		Collections.sort(result);
		bw.write(String.valueOf(result.size()));
		bw.newLine();
		for(int i : result) {
			bw.write(String.valueOf(i));
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
	
	static void bfs(int r, int c) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(r,c));
		visited[r][c] = true;
		int count = 1;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			for(int d=0; d<4; d++) {
				int nr = cur.x + dr[d];
				int nc = cur.y + dc[d];
				if(check(nr, nc) && map[nr][nc] == 1) {
					count++;
					visited[nr][nc] = true;
					q.add(new Point(nr, nc));
				}
			}
		}
		
		result.add(count);
		
	}
	
	static boolean check(int r, int c) {
		return (r>=0 && r<N && c>=0 && c<N && !visited[r][c]);
	}

}
