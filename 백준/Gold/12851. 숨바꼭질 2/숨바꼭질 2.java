import java.util.*;
import java.io.*;

public class Main {
    static final int MAX = 100001; // 위치의 최대 범위
    static int N, K;
    static int[] visited = new int[MAX]; // 각 위치에 도달하는 최소 시간을 저장하는 배열
    static int[] count = new int[MAX];   // 각 위치에 최소 시간으로 도달하는 방법의 수를 저장하는 배열
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 입력 받기
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        
        // 시작 위치와 목적지가 같은 경우
        if (N == K) {
            System.out.println(0);
            System.out.println(1);
            return;
        }
        
        Arrays.fill(visited, -1); // 방문하지 않은 위치를 -1로 초기화
        bfs();
        System.out.println(visited[K]); // 최소 시간 출력
        System.out.println(count[K]);   // 방법의 수 출력
    }
    
    static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(N);
        visited[N] = 0; // 시작 위치의 시간은 0
        count[N] = 1;   // 시작 위치로의 방법의 수는 1
        
        while (!queue.isEmpty()) {
            int now = queue.poll();
            
            int[] nextPositions = {now - 1, now + 1, now * 2};
            for (int next : nextPositions) {
                if (next < 0 || next >= MAX) continue; // 범위를 벗어나면 무시
                
                // 처음 방문하는 경우
                if (visited[next] == -1) {
                    visited[next] = visited[now] + 1;
                    count[next] = count[now];
                    queue.offer(next);
                }
                // 이미 방문했지만 최단 시간으로 도달하는 경우
                else if (visited[next] == visited[now] + 1) {
                    count[next] += count[now];
                }
            }
        }
    }
}
