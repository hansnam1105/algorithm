import java.io.*;
import java.util.*;

public class Main {
	
	static class Pool implements Comparable<Pool> {
		int s, e;
		
		public Pool(int s, int e) {
			this.s = s;
			this.e = e;
		}

		@Override
		public int compareTo(Pool o) {
			if(this.s == o.s) {
				return o.e - this.e;
			}
			return this.s-o.s;
		}
		
		
	}

	static int N, L;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Pool> pq = new PriorityQueue<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			pq.add(new Pool(start, end));
		}
		
		int minVal = 0;
		int fill = 0;
		
		while(!pq.isEmpty()) {
			Pool cur = pq.poll();
			
			// 웅덩이 이미 널빤지 있음
			if(cur.e < fill) {
				continue;
			}
			
			if(fill < cur.s) {
				fill = cur.s;
			}
			
			int remain = (cur.e - fill) % L; // 널빤지 남는 구간 구하기
			
			minVal += (cur.e - fill) / L; // 널빤지 개수 추가
			fill = cur.e;
			
			if(remain != 0) {
				minVal++;
				fill += L - remain;
			}
			
		}
		
		bw.write(String.valueOf(minVal));
		bw.flush();
		bw.close();
		

	}

}
