import java.io.*;
import java.util.*;

/*
 * 외판원 순회 문제
 * 1~N 번호 도시
 * 한 도시에서 출발해 도시 모두 거쳐 다시 원래 돌아오는 순회 여행 경로
 * 한번 갔ㄷ너 도시로는 다시 x 
 * 
 */

public class Main {

    static int N;
    static int[][] arr, dp;
    static int INF = 99_999_999;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 외판원 순회 최소 비용 저장용
        dp = new int[N][(1 << N) - 1];
        for (int i = 0; i < N; i++)
            Arrays.fill(dp[i], -1);

        bw.write(String.valueOf(dfs(0, 1)));
        bw.flush();
        bw.close();

    }

    static int dfs(int now, int visit) {
        if (visit == (1 << N) - 1) {
            if (arr[now][0] == 0)
                return INF;
            return arr[now][0];
        }
        if (dp[now][visit] != -1)
            return dp[now][visit];
        dp[now][visit] = INF;
        for (int i = 0; i < N; i++) {
            if ((visit & (1 << i)) == 0 && arr[now][i] != 0) {
                dp[now][visit] = Math.min(dp[now][visit], dfs(i, visit | (1 << i)) + arr[now][i]);
            }
        }

        return dp[now][visit];
    }
}
