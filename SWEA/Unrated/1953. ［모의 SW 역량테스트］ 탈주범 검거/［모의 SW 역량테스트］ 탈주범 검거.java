/**
 * 입력값
 * #TestCase
 * Row, Column, startRow, startCol, Time
 * Map
 * 수정해보자
 * @author SSAFY
 *
 */

import java.io.*;
import java.util.*;

public class Solution {
	
	static int N, M, sr, sc, len, answer;
	static int[][] map;
	static boolean[][] visited;
	
	// 방향
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
	static int[][] tunnelDir = {
			{}, // 0
			{0,1,2,3}, // 1
			{0,2}, // 2
			{1,3}, // 3
			{0,1}, // 4
			{1,2}, // 5
			{2,3}, // 6
			{0,3} // 7
	};
	
	
	static class Node{
		int r, c;
		
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			answer=1;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
		    sr = Integer.parseInt(st.nextToken());
			sc = Integer.parseInt(st.nextToken());
			len = Integer.parseInt(st.nextToken());
			
			map = new int[N][M]; 
			visited = new boolean[N][M];
		
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			bfs();
			
			bw.write("#" + tc + " " + answer + "\n");
			
		}
		bw.flush();
		bw.close();

	}
	
	static void bfs() {
		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(sr,sc));
		visited[sr][sc] = true;
		int time=1;
		
		if(time==len) return;
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			for(int s =0; s<size; s++) {
				Node cur = q.poll();
				int curTunnel = map[cur.r][cur.c];

				for(int i=0; i<tunnelDir[curTunnel].length;i++) {
					int curDir = tunnelDir[curTunnel][i];
					int nr = cur.r + dir[curDir][0];
					int nc = cur.c + dir[curDir][1];
				
					if(check(nr, nc)) {
						//다음 노드가 반대 방향 가지고 있으면 canGo == true 
						if(!canGo(nr,nc,(curDir+2)%4)) continue;
						if(visited[nr][nc]) continue;

						visited[nr][nc] = true;
						answer++;
						q.add(new Node(nr,nc));
					}
					
				}
			}
			if(++time==len) return;
		}
	}
	
	static boolean canGo(int r, int c, int direct) {
		int curDir = map[r][c];
		for(int d : tunnelDir[curDir]) {
			if(d==direct) 
				return true;
		}
		return false;
	}
	
	static boolean check(int r, int c) {
		return (r>=0 && r<N && c>=0 && c<M && map[r][c] != 0);
	}
}
