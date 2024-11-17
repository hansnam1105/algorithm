import java.io.*;
import java.util.*;

public class Main {
	
	static final int MAX = 100_000;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		
		sb.append(dijkstra(N,K));
		System.out.println(sb);
			
	}
	
	static int dijkstra(int start, int target) {
		int[] dist = new int[MAX+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[1], b[1]));
		pq.offer(new int[] {start, 0});
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			int pos = cur[0];
			int time = cur[1];
			
			if(pos == target) return time;
			
			if(time > dist[pos]) continue; // 이미 더 짧은 시간 존재시
			
			// 순간이동
			if(pos * 2 <= MAX && time < dist[pos*2]) {
				dist[pos*2] = time;
				pq.offer(new int[] {pos*2, time});
			}
			
			// 뒤로 이동 (1초)
            if (pos - 1 >= 0 && time + 1 < dist[pos - 1]) {
                dist[pos - 1] = time + 1;
                pq.offer(new int[] { pos - 1, time + 1 });
            }

            // 앞으로 이동 (1초)
            if (pos + 1 <= MAX && time + 1 < dist[pos + 1]) {
                dist[pos + 1] = time + 1;
                pq.offer(new int[] { pos + 1, time + 1 });
            }
			
		}
		
		return -1;
		
		
	}

}
