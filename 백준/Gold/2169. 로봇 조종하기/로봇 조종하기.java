import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N][M];

        dp[0][0] = map[0][0];

        // 첫 행 오른쪽 이동
        for(int j=1; j<M; j++) {
            dp[0][j] = dp[0][j-1] + map[0][j];
        }

        // 두번째 행 부터 각 행 최대값 계산
        for(int i=1; i<N; i++) {
            int[] left = new int[M];
            int[] right = new int[M];

            left[0] = dp[i-1][0] + map[i][0];
            for(int j=1; j<M; j++){
                left[j] = Math.max(left[j-1], dp[i-1][j]) + map[i][j];
            }

            right[M-1] = dp[i-1][M-1] + map[i][M-1];
            for(int j=M-2; j>=0; j--){
                right[j] = Math.max(right[j+1], dp[i-1][j]) + map[i][j];
            }

            for(int j=0; j<M; j++){
                dp[i][j] = Math.max(left[j], right[j]);
            }
        }

        System.out.println(dp[N-1][M-1]);

    }
}
