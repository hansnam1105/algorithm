import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static long[] arr;
    static long[] answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);
        answer = new long[3];
        findClosestToZero();
        Arrays.sort(answer);
        bw.write(answer[0] + " " + answer[1] + " " + answer[2]);
        bw.flush();
        bw.close();

    }

    static void findClosestToZero() {
        long minVal = 3000000000L;
        for (int i = 0; i < N - 2; i++) {
            int left = i + 1;
            int right = N - 1;
            while (left < right) {
                long sum = arr[left] + arr[right] + arr[i];
                if (Math.abs(sum) < Math.abs(minVal)) {
                    answer[0] = arr[i];
                    answer[1] = arr[left];
                    answer[2] = arr[right];
                    minVal = sum;
                }
                if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

    }
}
