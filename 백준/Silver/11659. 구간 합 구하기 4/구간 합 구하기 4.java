/**
 * 구간 합구하기
 */
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int idx=1; idx<=N; idx++) {
			arr[idx] = arr[idx-1] + Integer.parseInt(st.nextToken());	
		}		
		for(int r=0; r<M; r++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			
			sb.append(arr[j] - arr[i - 1]).append("\n");
			
		}
		
		System.out.println(sb);
		
		

	}

}
