import java.util.*;
import java.io.*;

public class Main {
	
	static int[][] box;
	static int M, N;
	static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
	static int[] dy = {0, 0, -1, 1}; // 상, 하, 좌, 우
	
	static class Tomato{
		int y;
		int x;
		
		public Tomato(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		List<Tomato> list = new ArrayList<>();
		
		box = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
				if(box[i][j] == 1) {
					list.add(new Tomato(i, j));
				}
			}
		}
		
		System.out.println(bfs(list));
		

	}
	
	public static int bfs(List<Tomato> list) {
		
		Queue<Tomato> q = new LinkedList<>();
		for(int i=0; i<list.size(); i++) {
			q.offer(list.get(i));
		}
		
		while(!q.isEmpty()) {
			Tomato cur = q.poll();
			
			for(int i=0 ;i<4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				
				if(ny<0 || ny>=N || nx<0 || nx>=M) {
					continue;
				}
				if(box[ny][nx] == 0) {
					box[ny][nx] = box[cur.y][cur.x] +1;
					q.offer(new Tomato(ny, nx));
				}
				

			}
		}
		
		int count = Integer.MIN_VALUE;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(box[i][j] == 0) {
					return -1;
				}
				count = Math.max(count, box[i][j]);
			}
		}
		
		if(count == 1) {
			return 0;
		} else {
			return count-1;
		}
	}
	
}
