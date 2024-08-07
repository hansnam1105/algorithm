import java.util.*;
import java.io.*;

public class Main {
    
    static StringBuilder sb = new StringBuilder();
    /* n은 다리를 건너는 트럭의 수
     * w은 다리의 길이
     * L은 다리의 최대하중
     * 
     */
    static int n, w, L;
    static int[] truck;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        
        truck = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            truck[i] = Integer.parseInt(st.nextToken());
        }
        
        System.out.println(calculateTime());
    }
    
    public static int calculateTime() {
        /*
         * currW는 다리 현재 하중
         */
    	int idx = 0;
        int currW = 0;
        int time = 0;
        Queue<Integer> bridge = new LinkedList<>();
        
        // 다리 초기화
        for(int i = 0; i < w; i++) {
            bridge.add(0);
        }
        
        while (idx < n) {
            time++;
            // 다리에서 트럭 제거
            currW -= bridge.poll();
            
            // 새로운 트럭이 다리에 올라갈 수 있는지 확인
            if (currW + truck[idx] <= L) {
                bridge.add(truck[idx]);
                currW += truck[idx];
                idx++;
            } else {
                bridge.add(0);
            }
        }
        
        // 마지막 트럭이 다리를 건너는 시간 추가
        time += w;
        
        return time;
    }
}
