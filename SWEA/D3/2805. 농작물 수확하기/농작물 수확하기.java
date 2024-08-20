/**
 * SWEA 2805 농작물 수확하기
 * 농장 크기 홀수
 * 홀수로 
 * 아래 숫자가 마름모 가로 선마다 길이라 하면
 * 	 1
 * 1 3
 * 3 5
 * 1 3
 *   1
 * 이런식으로 된다
 * 그렇기에 for문을 통해 가로 길이를 갱신하며 합을 구해주면 된다
 *   
 */

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Solution {
	
	static int N, total;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			N = parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N][N];
			total = 0;
			
			for(int i=0; i<N; i++) {
				String temp = br.readLine();
				for(int j=0; j<N; j++) {
					map[i][j] = temp.charAt(j) -'0';
				}
			}
			
			int center = (N-1)/2; // 중앙 좌표
			int colLength = N-1; // 가로 길이
			// 마름모를 반으로 나누어 윗 부분 먼저 연산
			for(int i=center; i>=0; i--) {
				// 마름모를 세로로 나누어 오른쪽 먼저 연산
				for(int j=center; j<=center+colLength/2; j++) {
					total += map[i][j];
				}
				// 왼쪽 연산
				for(int k=center; k>=center-colLength/2; k--) {
					total += map[i][k];
				}
				// 가운데 값 제거
				total -= map[i][center];
				// 그 다음 가로 선 부분 연산을 위해 -2
				colLength -= 2;
			}
			colLength = N-3; // 값 초기화
			// 마름모 아랫 부분 연산
			for(int i=center+1; i<N; i++) {
				for(int j=center; j<=center+colLength/2; j++) {
					total += map[i][j];
				}
				for(int k=center; k>=center-colLength/2; k--) {
					total += map[i][k];
				}
				total -= map[i][center];
				colLength -= 2;
			}
			
			sb.append("#").append(tc).append(" ").append(total).append("\n");
			
		}
		
		System.out.println(sb);
	}
	

}
