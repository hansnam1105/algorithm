/**
 * 배낭 가치 있게
 * N 개의 물건
 * 무게 W 가치 V
 * 최대 K 개 만큼 가능
 * 입력 N, K
 * W, V
 * 
 * DP 문제
 * 2차원 배열 활용
 * dp[N+1][K+1] / dp[i][w] i번째 물건까지 고려했을때 무게 w
 * 
 * 
 */

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] item = new int[N + 1][2];


        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            item[i][0] = Integer.parseInt(st.nextToken());
            item[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N + 1][K + 1];

        for (int k = 1; k <= K; k++) {
            for (int i = 1; i <= N; i++) {
                if (item[i][0] <= k) {
                    dp[i][k] = Math.max(dp[i - 1][k], dp[i - 1][k - item[i][0]] + item[i][1]);
                } else {
                    dp[i][k] = dp[i - 1][k];
                }
            }
        }

        bw.write(String.valueOf(dp[N][K]));
        bw.flush();
        bw.close();
        
    }
}
