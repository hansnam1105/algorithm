import java.io.*;
import java.util.*;

public class Main {

	static int N, M, K;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int depth = 0;
		while((1<<depth) < N) {
			depth++;
		}
		depth++;
		int arraySize = 1 << depth;
		int startIndex = 1 << (depth -1);
		long[] tree = new long[arraySize];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			long in = Long.parseLong(st.nextToken());
			tree[startIndex +i] = in;
		}
		
		for(int i=startIndex -1; i >= 1; i--) {
			tree[i] = tree[i*2] + tree[i*2+1];
		}
	
		
		for(int i=0; i<M+K; i++) {
			st = new StringTokenizer(br.readLine());
			int opt = Integer.parseInt(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			
			if(opt == 1) {
				int target = (int) (startIndex + b -1);
				long diff = c - tree[target];
				while(target > 0) {
					tree[target] += diff;
					target /= 2;
				}
			} else {
				int s = (int) b + startIndex - 1;
				int e = (int) c + startIndex - 1;
				long ans = 0;
				while (s <= e) {
					if(s % 2 == 1) ans += tree[s];
					if(e % 2 == 0) ans += tree[e];
					s = (s + 1) / 2;
					e = (e - 1) / 2;
				}
				sb.append(ans);
				sb.append("\n");
			}
		}
		
		System.out.println(sb);
		
	}
}
