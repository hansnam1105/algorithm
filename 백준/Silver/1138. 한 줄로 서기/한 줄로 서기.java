import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] memory = new int[N];
        int[] result = new int[N];
        Arrays.fill(result, 0);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }

        for (int height = 1; height <= N; height++) {
            int count = memory[height - 1];
            int idx = 0;

            while (true) {
                if (result[idx] == 0) {
                    if (count == 0) {
                        result[idx] = height;
                        break;
                    }
                    count--;
                }
                idx++;
            }
        }
        
        for (int h : result) {
            System.out.print(h + " ");
        }
    }
}