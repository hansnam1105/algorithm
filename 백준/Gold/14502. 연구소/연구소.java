import java.util.*;
import java.io.*;

public class Main {

	static int N, M, maxVal;
	static int[][] map;
	static int wall = 3;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static class Virus{
		int y, x;
		public Virus(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static List<Virus> list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		list = new ArrayList<>();
		maxVal = Integer.MIN_VALUE;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				int temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;
				if(temp == 2) {
					list.add(new Virus(i,j));
				}
			}
		}
		
		dfs(0);
		
		bw.write(String.valueOf(maxVal));
		bw.flush();
		bw.close();
	}
	
	public static void dfs(int count) {
		if(count == wall) {
			bfs();
			return;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1;
					dfs(count+1);
					map[i][j] = 0;
				}
				
			}
		}
		
	}
	
	public static void bfs() {
		int[][] tempMap = new int[N][M]; // 깊은 복사 중요
        for (int i = 0; i < N; i++) {
            tempMap[i] = map[i].clone();
        }
        
        Queue<Virus> q = new LinkedList<>(list);
		
		while(!q.isEmpty()) {
			Virus cur = q.poll();
			for(int i=0; i<4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				
				if(ny < 0 || ny>=N || nx < 0 || nx>=M) {
					continue;
				}
				if(tempMap[ny][nx] == 0) {
					q.add(new Virus(ny, nx));
					tempMap[ny][nx] = 2;

				}
			}
		}
		int count = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(tempMap[i][j] == 0) {
					count++;
				}
			}
		}
		maxVal = Math.max(maxVal, count);
		
	}

}
