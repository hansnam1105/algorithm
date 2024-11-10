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
        
        // LIS 역추적하여 구성하기
        Stack<Integer> stack = new Stack<>();
        while (lastIdx != -1) {
            stack.push(arr[lastIdx]);
            lastIdx = prevIdx[lastIdx];
        }
        
        // 결과 출력
        bw.write(String.valueOf(lis.size()) + "\n");
        while (!stack.isEmpty()) {
            bw.write(stack.pop() + " ");
        }
        bw.flush();
        bw.close();
    }
    
    // lower bound 구현 (이분 탐색을 이용해 target의 위치를 찾음)
    static int lowerBound(int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) / 2;
            
            if (lis.get(mid) >= target) {
                right = mid;  // target 이상인 경우 mid가 정답이 될 수 있으므로 right = mid
            } else {
                left = mid + 1;  // target보다 작은 경우 더 오른쪽을 탐색
            }
        }
        return right;
    }
}
