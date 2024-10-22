import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * P12850
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int MOD = 1000_000_000;

        int N = Integer.parseInt(br.readLine());

        // N 번째 자리, k 수, n번째 자리 어떤 숫자
        long[][][] dp = new long[N + 1][10][1 << 10];

        for (int k = 1; k <= 9; k++) {
            dp[1][k][1 << k] = 1;
        }

        for (int n = 2; n <= N; n++) {
            for (int k = 0; k <= 9; k++) {
                for (int b = 0; b < (1 << 10); b++) {
                    int currBit = b | (1 << k);
                    if (k == 0) {
                        dp[n][k][currBit] += dp[n - 1][k + 1][b] % MOD;
                    } else if (k == 9) {
                        dp[n][k][currBit] += dp[n - 1][k - 1][b] % MOD;
                    } else {
                        dp[n][k][currBit] += (dp[n - 1][k - 1][b] % MOD + dp[n - 1][k + 1][b] % MOD);
                    }

                    dp[n][k][currBit] %= MOD;
                }

            }
        }
        int answer = 0;
        for (int i = 0; i <= 9; i++) {
            answer += dp[N][i][(1 << 10) - 1] % MOD;
            answer %= MOD;
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();

    }
}