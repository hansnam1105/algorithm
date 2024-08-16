/**
 * 햄버거 다이어트 [부분집합]
 */

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Solution {

	static int N, L;
	static int maxTaste = 0;
	static int[][] material;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = parseInt(st.nextToken());
			L = parseInt(st.nextToken());
			
			material = new int[N][2];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				material[i][0] = parseInt(st.nextToken());
				material[i][1] = parseInt(st.nextToken());
			}
			
			maxTaste = 0;
			boolean[] isSelected = new boolean[N];
			powerSet(0, 0, 0, isSelected);	
			sb.append("#").append(tc).append(" ").append(maxTaste).append("\n");
			
		}
		System.out.println(sb);
	}
	
	public static void powerSet(int count, int taste, int calorie, boolean[] isSelected) {

		if(calorie>L) {
			return;
		}
		if(count == N) {
			if(calorie<=L) {
				maxTaste = Math.max(maxTaste, taste);
			}
			return;
		}
		
		isSelected[count] = true;
		powerSet(count+1, taste+material[count][0], calorie+material[count][1], isSelected);
		isSelected[count] = false;
		powerSet(count+1, taste, calorie, isSelected);
		
		
	}

}