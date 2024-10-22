import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 2];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int i = 0;
        int answer = 0;
        while (i < N) {
            if (arr[i] > 0) {
                int temp = arr[i];
                answer += 3 * temp;
                temp = Math.min(temp, arr[i + 1]);
                answer += 2 * temp;
                arr[i + 1] -= temp;
                temp = Math.min(temp, arr[i + 2] - Math.min(arr[i + 2], arr[i + 1]));
                answer += 2 * temp;
                arr[i + 2] -= temp;

            }
            i++;
        }
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}
