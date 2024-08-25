
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); 
        int M = Integer.parseInt(st.nextToken());
        
        int[] trees = new int[N];
        st = new StringTokenizer(br.readLine());
        int max = 0;

        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            if (trees[i] > max) {
                max = trees[i];
            }
        }

        int low = 0;
        int high = max;
        int result = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            long sum = 0;

            for (int tree : trees) {
                if (tree > mid) {
                    sum += tree - mid;
                }
            }

            if (sum >= M) {
                result = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    
    }
    
}
