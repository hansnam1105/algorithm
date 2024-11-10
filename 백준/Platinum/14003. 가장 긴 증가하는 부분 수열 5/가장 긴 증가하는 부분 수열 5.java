import java.io.*;
import java.util.*;

public class Main {
    
    static int N;
    static int[] arr;
    static int[] prevIdx;
    static int[] lisIdx;
    static ArrayList<Integer> lis;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        prevIdx = new int[N];
        lisIdx = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        lis = new ArrayList<>();
        int lastIdx = -1;

        // LIS 계산하기
        for (int i = 0; i < N; i++) {
            if (lis.isEmpty() || lis.get(lis.size() - 1) < arr[i]) {
                prevIdx[i] = (lis.isEmpty()) ? -1 : lisIdx[lis.size() - 1];
                lis.add(arr[i]);
                lisIdx[lis.size() - 1] = i;
                lastIdx = i;
            } else {
                int idx = lowerBound(0, lis.size() - 1, arr[i]);
                lis.set(idx, arr[i]);
                lisIdx[idx] = i;
                prevIdx[i] = (idx == 0) ? -1 : lisIdx[idx - 1];
            }
        }
        
        Stack<Integer> stack = new Stack<>();
        while (lastIdx != -1) {
            stack.push(arr[lastIdx]);
            lastIdx = prevIdx[lastIdx];
        }
        
        bw.write(String.valueOf(lis.size()) + "\n");
        while (!stack.isEmpty()) {
            bw.write(stack.pop() + " ");
        }
        bw.flush();
        bw.close();
    }
    
    static int lowerBound(int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) / 2;
            
            if (lis.get(mid) >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }
}
