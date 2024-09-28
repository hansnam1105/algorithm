import java.io.*;
import java.util.*;

/**
 * DP 삼각형 가장 아래부터 합
        7
      3   8
    8   1   0
  2   7   4   4
4   5   2   6   5

->
  7   12   10   10
4   5   2   6   5
 */

public class Main {

    static int N;
    static int[][] arr;
    static Integer[][] dp;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        arr = new int[N][N];
        // 정부 범위가 0~9999 그렇기에 int 말고 Integer로 선언
        // Integer 초기화시 배열 값은 0 이 아닌 null
        dp = new Integer[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j <= i; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            dp[N - 1][i] = arr[N - 1][i];
        }

        bw.write(String.valueOf(sum(0, 0)));
        bw.flush();
        bw.close();

    }

    static int sum(int depth, int idx) {
        if (depth == N - 1) {
            return dp[depth][idx];
        }

        if (dp[depth][idx] == null) {
            dp[depth][idx] = Math.max(sum(depth + 1, idx), sum(depth + 1, idx + 1)) + arr[depth][idx];
        }

        return dp[depth][idx];
    }
}
