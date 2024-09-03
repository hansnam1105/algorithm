import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Solution {

	static int N;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] map;
	
	static class Element implements Comparable<Element>{
		int r, c;
		int time;
		
		public Element(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}
		
		@Override
		public int compareTo(Element e) {
			return this.time - e.time;
		}
	}
	
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			N = parseInt(br.readLine());
			map = new int[N][N];			
			for(int i=0; i<N; i++) {
				char[] ch = br.readLine().toCharArray();
				for(int j=0; j<N; j++) {
					map[i][j] = ch[j] - '0';
				}
			}
			
			bw.write("#" + tc + " " + getMinDistance(0,0,N-1,N-1) + "\n");
		}
		
		bw.flush();
		bw.close();
	}
	
	static int getMinDistance(int sr, int sc, int er, int ec) {
		
		final int INF = Integer.MAX_VALUE;
		boolean[][] visited	= new boolean[N][N];
		int[][] minTime = new int[N][N];
		
		PriorityQueue<Element> pq = new PriorityQueue<>();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				minTime[i][j] = INF;
			}
		}
		
		minTime[sr][sc] = 0;
		pq.offer(new Element(sr, sc, minTime[sr][sc]));
		
		while(!pq.isEmpty()) {
			Element cur = pq.poll();
			int r = cur.r;
			int c = cur.c;
			int time = cur.time;
			
			// step 1
			if(visited[r][c]) continue;
			visited[r][c] = true;
			
			if(r==er && c==ec) {
				return time;
			}
			
			// step 2
			for(int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(check(nr,nc) && !visited[nr][nc] && minTime[nr][nc] > time+map[nr][nc]) {
					minTime[nr][nc] = time+map[nr][nc];
					pq.offer(new Element(nr,nc, minTime[nr][nc]));
				}
				
			}
			
		}
		
		
		return -1;
	}
	
	static boolean check(int r, int c) {
		return (r>=0 && r<N && c>=0 && c<N);
	}

}
