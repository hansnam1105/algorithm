import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] arr;
    static ArrayList<Integer> lis;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        // 입력 받기
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        lis = new ArrayList<>();
        
        // LIS 계산하기
        for (int i = 0; i < N; i++) {
            if (lis.isEmpty() || lis.get(lis.size() - 1) < arr[i]) {
                lis.add(arr[i]);  // 현재 원소가 LIS의 마지막 원소보다 크면 추가
            } else {
                int idx = lowerBound(0, lis.size() - 1, arr[i]);
                lis.set(idx, arr[i]);  // 현재 원소가 LIS의 어느 위치에 들어갈지 찾고 갱신
            }
        }
        
        // 결과 출력
        bw.write(String.valueOf(lis.size()));
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