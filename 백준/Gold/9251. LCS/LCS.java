import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str1, str2;
        str1 = br.readLine();
        str2 = br.readLine();

        int length1 = str1.length();
        int length2 = str2.length();

        int dp[][] = new int[length1 + 1][length2 + 1];
        /*
         * dp[0][0] = 0
         * ACAYKP
         * CAPCAK
         * {A,C,A,K} -> 4
         * 맨앞 문자부터 차례대로
         */
        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }

            }
        }

        bw.write(String.valueOf(dp[length1][length2]));
        bw.flush();
        bw.close();
    }
}
