import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] arr, output;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        output = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        Combination(0);

        System.out.println(sb);

    }

    static void Combination(int depth) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(String.valueOf(output[i]) + " ");
            }
            sb.append("\n");
            return;
        }
        int prev = 0;
        for (int i = 0; i < N; i++) {
            if (arr[i] != prev) {
                output[depth] = arr[i];
                prev = arr[i];
                Combination(depth + 1);
            }
        }

    }
}
