import java.io.*;
import java.util.*;

public class Main {

    static int N, S, count;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        count = 0;
        findSubset(0, 0);

        if (S == 0) {
            count--;
        }

        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
    }
    
    static void findSubset(int index, int sum) {
        if (index == N) {
            if (sum == S) {
                count++;
            }
            return;
        }

        findSubset(index + 1, sum + arr[index]);
        findSubset(index+1, sum);
    }
}
