import java.util.Scanner;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Solution
{
    static class Core {
		
		int y, x;
		
		
		public Core(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
    
    static StringBuilder sb = new StringBuilder();
	static int[][] core;
	static int N, minWireLength, maxCore;
	static List<Core> coreList;
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, 1, -1};

    
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());
		for(int t=1; t<=tc; t++) {
			// 7<= N <= 12
			N  = Integer.parseInt(br.readLine());
			core = new int[N][N];
			coreList =  new ArrayList<>();
			// 0은 빈 cell, 1은 core의미
			// core은 1<= core <= 12
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					int c = Integer.parseInt(st.nextToken());
					if(c == 1) {
						core[i][j] = 1;
						
						if(i==0 || j==0 || i==N-1 || j==N-1) {
							continue;							
						}
						//coreList 에 core 값 추가
						coreList.add(new Core(i,j));
					} //if end
				}
			} //for end
			
			minWireLength = Integer.MAX_VALUE;
			maxCore = Integer.MIN_VALUE;
			
			search(0, 0, 0);
			
			sb.append("#"+t+" "+minWireLength+"\n");
			
		}	
		
		System.out.println(sb);
	}
    
    public static void search(int idx, int coreCnt, int wireCnt) {
		if(idx == coreList.size()) {
			if(maxCore<coreCnt) {
				maxCore = coreCnt;
				minWireLength = wireCnt;
			} else if(maxCore == coreCnt) {
				minWireLength = Math.min(wireCnt, minWireLength);
			}
			return;
		}
		
		int x = coreList.get(idx).x;
		int y = coreList.get(idx).y;
		
		for(int i=0; i<4; i++) {
			int count = 0, nx = x, ny = y;
			
			while(true) {
				nx+=dx[i];
				ny+=dy[i];
				
				if(nx<0 || nx>=N || ny<0 || ny>=N) {
					break;
				}
				if(core[ny][nx] == 1) {
					count = 0;
					break;
				}
				count++;
			}
			
			int final_x = x;
			int final_y = y;
			
			for(int j=0; j<count; j++) {
				final_x += dx[i];
				final_y += dy[i];
				
				core[final_y][final_x] = 1;
			}
			
			if(count==0) {
				search(idx+1, coreCnt, wireCnt);
			}
			else {
				search(idx+1, coreCnt+1, wireCnt+count);
			}
			
			final_x = x;
			final_y = y;
			
			for(int j=0; j<count; j++) {
				final_x += dx[i];
				final_y += dy[i];
				
				core[final_y][final_x] = 0;
			}
		}
	}
}