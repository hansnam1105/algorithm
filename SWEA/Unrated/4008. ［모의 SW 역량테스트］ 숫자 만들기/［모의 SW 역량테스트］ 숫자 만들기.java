import java.io.*;
import java.util.*;

public class Solution {

    static int N, maxVal, minVal, ans;
    static int[] num, operator;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T ; tc++){
			N = Integer.parseInt(br.readLine());
			
            num = new int[N];
			
            operator = new int[4];
			st = new StringTokenizer(br.readLine());
            for(int i=0; i<4; i++) {
				operator[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int n = 0; n < N; n++) {
				num[n] = Integer.parseInt(st.nextToken());
			}

            maxVal = Integer.MIN_VALUE; 
			minVal = Integer.MAX_VALUE;
			
			dfs(0, num[0]);
						
			sb.append("#").append(tc).append(" ").append(maxVal-minVal).append("\n");
		}
        System.out.println(sb);
    }

    static void dfs(int idx, int res) {

		if (idx == N - 1) {
			minVal = Math.min(res, minVal);
			maxVal = Math.max(res, maxVal);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if(operator[i] == 0) continue;
			operator[i]--;
			dfs(idx + 1, calculate(i, res, num[idx+1]));
			operator[i]++;
		}
	}

    static int calculate(int operation, int num1, int num2) {
		switch (operation) {
		case 0:
			return num1 + num2;
		case 1:
			return num1 - num2;
		case 2:
			return num1 * num2;
		case 3:
			return num1 / num2;
		default:
			return Integer.MIN_VALUE;
		}
	}
}
