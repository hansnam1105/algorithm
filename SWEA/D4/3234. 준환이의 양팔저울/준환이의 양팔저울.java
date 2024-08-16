import java.util.*;
import java.io.*;

public class Solution {
	
	static int sCount, N;
	static int left, right;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			sCount = 0;
			int[] selected = new int[N];
            boolean[] visited = new boolean[N];
			arr = new int[N];
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			makePermutation(0,selected, visited);
			sb.append("#").append(tc).append(" ").append(sCount).append("\n");
			
		}
		
		System.out.println(sb);
		
	}
	
	public static void makePermutation(int toSelect, int[] selected, boolean[] visited) {
		if(toSelect == N) {
			weight(0,0,0, selected);
			return;
		}
		for(int i=0; i<N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				selected[toSelect] = arr[i];
				makePermutation(toSelect+1, selected, visited);
				visited[i] = false;
			}
		}
	}
	
	public static void weight(int left, int right, int toSelect, int[] selected) {
		if(toSelect == N) {
			sCount++;
			return;
		}
		
		weight(left + selected[toSelect], right, toSelect+1, selected);
		
		if(left >= right + selected[toSelect]) {
			weight(left, right + selected[toSelect], toSelect+1, selected);
		}
	}

}
