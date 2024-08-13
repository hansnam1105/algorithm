import java.io.*;
import java.util.*;

/**
 * 1210. [S/W 문제해결 기본] 2일차 - Ladder1
 * 
 */
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		//아래서 위로 탐색하기 때문에 한번 올라가면 내려가지 않는다
		int[] dx = {1,-1,0};
		int[] dy = {0,0,-1};
		int[][] map;

		
		for(int tc=1; tc<=10; tc++) {
			sb.append("#").append(br.readLine()).append(" ");
			map = new int[100][100];
			//현재 x 값
			int curx = 0;
			
			for(int i=0; i<100; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<100; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 2) {
						// x값에 목적지 x 위치 
						curx = j;
					}
				}
			}
			
			int nx, ny;
			int cury = 99;
			// 최단으로 출발지 도착까지 탐색
			while(cury!=0) {
				for(int i=0; i<3; i++) {
					nx = curx + dx[i];
					ny = cury + dy[i];
					
					if(nx<0 || nx>=100 || ny<0 || ny>=100 || map[ny][nx] == 0) {
						continue;
					}
					curx = nx;
					cury = ny;
					map[ny][nx] = 0;
				}
			}
			sb.append(curx).append("\n");
			
			
			
		}
		System.out.println(sb);
		
	}

}
