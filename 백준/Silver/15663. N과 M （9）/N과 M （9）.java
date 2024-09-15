import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] arr;
    static int[] output;
    static boolean[] visited;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        output = new int[M];
        visited = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        comb(0);
        bw.flush();
        bw.close();
    }

    static void comb(int depth) throws Exception {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                bw.write(String.valueOf(output[i]) + " ");
            }
            bw.newLine();
            return;
        }
        int prev = 0;
        for (int i = 0; i < N; i++) {
            if (!visited[i] && arr[i] != prev) {
                visited[i] = true;
                output[depth] = arr[i];
                prev = arr[i];
                comb(depth + 1);
                visited[i] = false;
            }
        }
        
    }
}