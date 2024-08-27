import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str1 = br.readLine();
        String str2 = br.readLine();

        int len1 = str1.length();
        int len2 = str2.length();
        int maxLength = 0;

        // 2차원 배열 row는 단어1 길이+1, column은 단어2 길이+1 사이즈
        int[][] dp = new int[len1 + 1][len2 + 1];

        // dp 공식 if 단어 같으면 +1 해주고 계속 다음 단어 같으면 +1을 해준다
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    maxLength = Math.max(maxLength, dp[i][j]);
                }
            }
        }

        bw.write(String.valueOf(maxLength));
        bw.flush();
        bw.close();
        br.close();
    }
}
