/**
 * 비트마스킹을 활용한 소문난 칠공주 
 * 비트마스킹 부분은 gpt 도움 받음
 * 5x5 map 을 25비트 비트마스킹으로 처리
 * BFS를 통해 7 칸 연결되었는지 확인
 * 
 */

import java.io.*;
import java.util.*;

public class Main {
	
	static final int len = 5;
	static char[][] map;
	static int ans;
	static int[] selected = new int[7];
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
		

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		map = new char[len][len];
		
		for(int i=0; i<len; i++) {
			map[i] = br.readLine().toCharArray();
		}
		ans = 0;
		backtracking(0,0,0,0);
		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
		
	}
	
	static void backtracking(int depth, int countY, int start, int mask) {
		if(countY>=4) {
			return;
		}
		
		if(depth == 7) {
			if(bfs(mask)) {
				ans++;
			}
			return;
		}
		
		// 백트레킹을 통해 7공주 생성 5*5 25 배열을 1차원으로 만들어 생성 비트마스킹 활용
		for(int i=start; i<25; i++) {
			// 해당 위치가 아직 선택되지 않았을 때
			if((mask & (1<<i)) == 0) {
				int newMask = mask | (1<<i);
				if(map[i/5][i%5] == 'Y') {
					backtracking(depth+1, countY+1, i+1, newMask);
				}
				else {
					backtracking(depth+1, countY, i+1, newMask);
				}
			}
		}
	}
	
	static boolean bfs(int mask) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[25];
		int start = -1;
		
        // 마스크에서 첫 번째로 선택된 칸(비트가 1인 칸)을 찾음
		for(int i=0; i<25; i++) {
			if((mask & (1 << i)) != 0) {
				start = i;
				break;
			}
		}
		
		if(start == -1) return false;
		
		q.offer(start);
		visited[start] = true;
		int num = 1;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			int r =  cur / 5;
			int c = cur % 5;
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				int nxt = 5*nr + nc;
				
                // 다음 칸이 유효한 범위 내에 있고, 아직 방문하지 않았으며, 선택된 칸인 경우
				if(isRange(nr, nc) && !visited[nxt] && (mask & (1<< nxt)) != 0) {
					visited[nxt] = true;
					num++;
					q.offer(nxt);					
				}
			}
		}
		return num == 7;
	}
	
	static boolean isRange(int r, int c) {
        return (r >= 0 && r < 5 && c >= 0 && c < 5);
    }

}
