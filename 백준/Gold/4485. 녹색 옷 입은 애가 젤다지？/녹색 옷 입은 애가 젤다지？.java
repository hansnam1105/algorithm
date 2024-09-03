import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] map;
	
	static class Element implements Comparable<Element>{
		int r, c;
		int rupee;
		
		public Element(int r, int c, int rupee) {
			this.r = r;
			this.c = c;
			this.rupee = rupee;
		}
		
		@Override
		public int compareTo(Element e) {
			return this.rupee - e.rupee;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int count = 1;
		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N == 0)
				break;
			map = new int[N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			bw.write("Problem " + count++ + ": " + getMinBlackRupee(0,0,N-1,N-1) + "\n");
		}
		
		bw.flush();
		bw.close();
		
		

	}
	
	static int getMinBlackRupee(int sr, int sc, int er, int ec) {
		
		final int INF = Integer.MAX_VALUE;
		boolean[][] visited	= new boolean[N][N];
		int[][] minRupee = new int[N][N];
		
		PriorityQueue<Element> pq = new PriorityQueue<>();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				minRupee[i][j] = INF;
			}
		}
		
		minRupee[sr][sc] = map[0][0];
		pq.offer(new Element(sr, sc, minRupee[sr][sc]));
		
		while(!pq.isEmpty()) {
			Element cur = pq.poll();
			int r = cur.r;
			int c = cur.c;
			int rupee = cur.rupee;
			
			
			if(visited[r][c]) continue;
			
			visited[r][c] = true;
			
			if(r==er && c==ec) {
				return rupee;
			}
			
			// step 2
			for(int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(check(nr,nc) && !visited[nr][nc] && minRupee[nr][nc] > rupee+map[nr][nc]) {
					minRupee[nr][nc] = rupee+map[nr][nc];
					pq.offer(new Element(nr,nc, minRupee[nr][nc]));
				}
				
			}
		}
		
		return -1;
	}
	
	static boolean check(int r, int c) {
		return (r>=0 && r<N && c>=0 && c<N);
	}

}
