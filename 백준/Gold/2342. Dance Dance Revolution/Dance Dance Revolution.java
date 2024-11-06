import java.io.*;
import java.util.*;

public class Main {

	/*
	 * 중앙 발 다른 지점 -> 2
	 * 인접 지점 -> 3
	 * 반대편 -> 4
	 */
	static int[][] power = 
		{ {0, 2, 2, 2, 2},
				{0, 1, 3, 4, 3},
				{0, 3, 1, 3, 4},
				{0, 4, 3, 1, 3},
				{0, 3, 4, 3, 1}
		};
	
	static int[] move;
	static int[][][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		move = new int[line.length-1];
		for(int i=0; i<line.length-1; i++) {
			move[i] = Integer.parseInt(line[i]);
		}
		
		dp = new int[5][5][line.length];
		
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		
		System.out.println(solve(0,0,0));
		
	}
	
	static int solve(int left, int right, int cnt) {
		// 모든 발판 밟음
		if(cnt == move.length) {
			return 0;
		}
		
		if(dp[left][right][cnt] != -1) {
			return dp[left][right][cnt];
		}
		// 왼발, 오른발 밟기 중 최솟값
		dp[left][right][cnt] = Math.min(solve(move[cnt], right, cnt+1) + power[left][move[cnt]], solve(left, move[cnt], cnt+1) + power[right][move[cnt]]);
			
		return dp[left][right][cnt];
	}
}
