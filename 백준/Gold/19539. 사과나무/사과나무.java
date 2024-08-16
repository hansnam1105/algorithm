import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] target = new int[N];
        int sum = 0;        // 전체 값의 합
        int twoCount = 0;   // 2로 나눌 수 있는 횟수의 합

        for (int i = 0; i < N; i++) {
            target[i] = Integer.parseInt(st.nextToken());
            sum += target[i];           // 전체 합
            twoCount += target[i] / 2;  // 각 값에서 2로 나눌 수 있는 횟수
        }

        // 전체 합이 3의 배수여야 하고, 두 번씩 나눌 수 있는 횟수가 충분해야 함
        if (sum % 3 == 0 && twoCount >= sum / 3) {
            bw.write("YES");
        } else {
            bw.write("NO");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
