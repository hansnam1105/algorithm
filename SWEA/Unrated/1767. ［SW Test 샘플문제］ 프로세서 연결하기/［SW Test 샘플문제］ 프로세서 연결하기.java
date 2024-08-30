/**
 * 프로세서 연결하기
 * 전선은 교차할수 없다
 * 가장자리는 이미 이어져있다
 * 
 * 1. Core class를 통해 코어 정보를 ArrayList<Core>에 등록
 * 멕시노스 입력 받을시 1일 때 코어 추가 + 가장자리는 추가안함
 * 
 * 2. 한코어의 전선을 우선 긋는다 -> 하나만 깊이 우선 탐색 -> DFS
 * DFS (코어 id, 전원연결된 코어, 전선길이) 3가지 변수로 재귀
 * 한 코어당 탐색하며 전원 연결된 코어를 추가하고, 전선 길이를 갱신한다
 */

import java.awt.Point;
import java.io.*;
import java.util.*;

public class Solution {

	static class Core{ // 코어 좌표 정보
		Point p;

		public Core(Point p) {
			this.p = p;
		}
	}
	
	static int N, maxCore, minWire;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] map;
	static ArrayList<Core> coreList;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			coreList = new ArrayList<>();
			int temp = 0;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					temp = Integer.parseInt(st.nextToken());
					if(temp == 1) {
						map[i][j] = 1;
						if(i==0 || j==0 || i==N-1 || j==N-1) { // 벽에 붙은 코어 추가 안함
							continue;							
						}
						coreList.add(new Core(new Point(i,j)));
					}
				}
			}
			
			maxCore = Integer.MIN_VALUE;
			minWire = Integer.MAX_VALUE;
			
			dfs(0, 0, 0);
			
			sb.append("#").append(tc).append(" ").append(minWire).append("\n");
		}
		
		System.out.println(sb);

	}
	
	public static void dfs(int idx, int coreCnt, int wireCnt) {
	    if (coreCnt + (coreList.size() - idx) < maxCore) return; // 가지치기: 최대 연결 가능한 코어 수가 maxCore보다 작으면 종료
	    
		if(idx == coreList.size()) {
			if(coreCnt > maxCore) { // 최대한 많은 코어 연결 위해 maxCore 갱신
				maxCore = coreCnt;
				minWire = wireCnt; // 전선길이 갱신
			}
			else if(coreCnt == maxCore) { // 탐색한 코어 == 최대 코어 같을시 전선 길이 최소 갱신
				minWire = Math.min(minWire, wireCnt);
			}
			
			return;
		}
		
				
		Core cur = coreList.get(idx);
		int cr = cur.p.x; // 현재 코어 Row 좌표
		int cc = cur.p.y; // 현재 코어 Column 좌표
		for(int i=0; i<4; i++) {
			int count = 0, nr = cr, nc = cc;
			while(true) {
				nr += dr[i];
				nc += dc[i];
				
				// 한 방향 범위 벗어나갔을때
				if(nr<0 || nr>=N || nc<0 || nc>=N) {
					break;
				}
				// 다른 코어 혹 전선 -> 방향 바꿔서 다시 카운팅
				if(map[nr][nc] == 1) {
					count = 0;
					break;
				}
				
				count++;
			}
			if(count>0) {
				// 코어 하나의 전선 탐색 후 map 에 전선 설치하기
				int installWireRow = cr;
				int installWireCol = cc;
				
				for(int j=0; j<count; j++) {
					installWireRow += dr[i];
					installWireCol += dc[i];
					map[installWireRow][installWireCol] = 1;
				}
				

				dfs(idx+1, coreCnt+1, wireCnt+count);
				
				// 다음 탐색을 위해 설치된 전선 다시 지우기
				installWireRow = cr;
				installWireCol = cc;
				
				for(int j=0; j<count; j++) {
					installWireRow += dr[i];
					installWireCol += dc[i];
					map[installWireRow][installWireCol] = 0;
				}
			}
			
						
		}
        dfs(idx + 1, coreCnt, wireCnt);
        
		
	}

}
