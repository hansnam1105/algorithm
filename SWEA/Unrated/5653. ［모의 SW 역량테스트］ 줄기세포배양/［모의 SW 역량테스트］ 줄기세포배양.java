import java.io.*;
import java.util.*;
public class Solution {
	
	static class Cell implements Comparable<Cell>{
		int r;
		int c;
		int life;
		int time;
		int status;
		
		
		public Cell(int r, int c, int life) {
			this.r = r;
			this.c = c;
			this.life = life;
			this.time = life;
		}
		
		void next() {
			switch(this.status) {
			case 0 :
				if(--time == 0) {
					status = 1;
				}
				break;
			case 1:
				if(++time == life) {
					status = 2;
				}
				break;
			}
		}
		
		@Override
		public int compareTo(Cell c) {
			return c.life - this.life;
		}
	}
	
	static int N, M, K;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static PriorityQueue<Cell> pq;
	static Queue<Cell> q;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			pq = new PriorityQueue<>();
			q = new LinkedList<>();
			
			visited = new boolean[N+K+1][M+K+1];
			 
			for(int i=K/2+1; i<N+K/2+1; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=K/2+1; j<M+K/2+1; j++) {
					int life = Integer.parseInt(st.nextToken());
					if(life != 0) {
						visited[i][j] = true;
						q.add(new Cell(i, j, life));
					}
				}
			}
			
			simulate();
			sb.append("#" + tc + " " + q.size() + "\n");
			
			
		}
		
		System.out.println(sb);

	}
	
	static void simulate() {
		while(K-- > 0) {
			int cellSize = q.size();
			for(int i=0; i<cellSize; i++) {
				Cell c = q.poll();
				
				if(c.status == 1) {
					pq.add(c);
				}
				
				c.next();
				
				if(c.status == 2) {
					continue;
				}
				
				q.add(c);
			}
			
			while(!pq.isEmpty()) {
				Cell cur = pq.poll();
				
				for(int i=0; i<4; i++) {
					int nr = cur.r + dr[i];
					int nc = cur.c + dc[i];
					
					if(visited[nr][nc]) continue;
					visited[nr][nc] = true;
					q.add(new Cell(nr, nc, cur.life));
					
				}
			}
			
			
		}
	}

}
