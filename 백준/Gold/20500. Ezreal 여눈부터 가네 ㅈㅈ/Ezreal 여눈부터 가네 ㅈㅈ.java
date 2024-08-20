
import java.io.*;
import static java.lang.Integer.parseInt;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long MOD = 1000000007L;
        int N = parseInt(br.readLine());
        // 0 :
        // 1 : 
        // 2 : 15
        // 3 : 555
        // 4 : 1155 1515 5115
        // 5 : 1515 1515 10515 10155 51555
        // 6 : 115155 115515 151515 151155 511515 511155 515115 551115 
        // 1의 자리는 5
        // 0 1 1 1 3 5 9 
        // dp[3] 까지 고정
        // dp[n] = dp[n-2] *3 + dp[n-3] * 2

        long[] dp = new long[1516];
        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;

        for (int i = 4; i <= N; i++) {
            dp[i] = ((dp[i - 2] * 3) % MOD  + (dp[i - 3] * 2) % MOD) % MOD;
        }


        bw.write(String.valueOf(dp[N]));
        bw.flush();
        bw.close();

    }
    
}
