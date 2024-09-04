import java.io.*;
import java.util.*;

public class Main {
    
    static class Print {
        int order, importance;
        
        public Print(int order, int importance) {
            this.order = order;
            this.importance = importance;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        
        for(int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 문서의 개수
            int M = Integer.parseInt(st.nextToken()); // 현재 몇 번째 문서
            
            Queue<Print> queue = new LinkedList<>();
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // 중요도를 기준으로 내림차순 정렬
            
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int importance = Integer.parseInt(st.nextToken());
                queue.offer(new Print(i, importance));
                pq.offer(importance); // 중요도를 PQ에 삽입
            }
            
            int count = 0;
            
            while (!queue.isEmpty()) {
                Print current = queue.poll();
                
                // 현재 문서의 중요도가 PQ의 가장 높은 중요도와 같다면 출력 가능
                if (current.importance == pq.peek()) {
                    pq.poll(); // 중요도 큐에서 제거
                    count++;
                    
                    // 출력한 문서가 우리가 찾는 문서인 경우
                    if (current.order == M) {
                        bw.write(count + "\n");
                        break;
                    }
                } else {
                    // 중요도가 높은 문서가 뒤에 있으므로 다시 큐에 삽입
                    queue.offer(current);
                }
            }
        }
        
        bw.flush();
        bw.close();
    }
}
