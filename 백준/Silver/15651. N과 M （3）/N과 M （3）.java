import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int M;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();

	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[M];
		
		dfs(0);
		System.out.println(sb);
	}
	
	public static void dfs(int n) {
		if(n == M) {
			for(int i=0; i<M; i++) {
				sb.append(arr[i] + " ");
			}
			sb.append('\n');
			return;
		}
		
		for(int i=0; i<N; i++) {
			arr[n] = i+1;
			dfs(n+1);
		}
	}

}
