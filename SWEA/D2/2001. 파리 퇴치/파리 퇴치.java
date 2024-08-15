import java.util.*;
import java.io.*;

public class Solution {
	
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N, M, maxVal;
		int[][] map;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			maxVal = 0;
			
			// (0,0) 부터 (N-M, N-M) 까지 탐색
			for(int i=0; i<=N-M; i++) {
				for(int j=0; j<=N-M; j++) {
					int sum = 0;
					for(int k=i; k<M+i; k++) {
						for(int l=j; l<M+j; l++) {
							sum+=map[k][l];
						}
					}
					maxVal = Math.max(maxVal, sum);
				}
			}
			
			sb.append("#").append(tc).append(" ").append(maxVal).append("\n");
			
			
		}
		System.out.println(sb);
	}

}
