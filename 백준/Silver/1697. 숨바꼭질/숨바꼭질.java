import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt(); // 수빈이의 시작 위치
        int K = sc.nextInt(); // 동생의 위치
        
        // 범위
        int MAX = 100000;
        
        // 방문 배열(또는 거리 저장 배열)
        // -1로 초기화해서 방문하지 않았음을 표시(또는 boolean[] visited 로 활용 가능)
        int[] dist = new int[MAX + 1];
        Arrays.fill(dist, -1);
        
        // BFS를 위한 큐
        Queue<Integer> queue = new LinkedList<>();
        
        // 초기 위치 설정
        dist[N] = 0;
        queue.offer(N);
        
        // BFS 시작
        while(!queue.isEmpty()) {
            int current = queue.poll();
            
            // 동생의 위치를 찾으면 종료
            if (current == K) {
                System.out.println(dist[current]);
                break;
            }
            
            // 이동할 수 있는 3가지 경우
            int[] nextPositions = {current - 1, current + 1, current * 2};
            
            for (int next : nextPositions) {
                // 범위 체크 및 방문 여부 체크
                if (next >= 0 && next <= MAX && dist[next] == -1) {
                    dist[next] = dist[current] + 1;
                    queue.offer(next);
                }
            }
        }
        
        sc.close();
    }
}
