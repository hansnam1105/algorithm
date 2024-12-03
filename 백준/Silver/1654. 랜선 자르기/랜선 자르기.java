import java.io.*;
import java.util.*;

public class Main {
		
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[K];
		
		long min = 0;
		long max = 0;
		long mid = 0;
		
		for(int i=0; i<K; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			if(arr[i] > max)
				max = arr[i];
			
		}
		
		max++;
		long n;
		while(min<max) {
			n = 0;
			mid = (min + max) /2;
			
			for(int i=0; i<K; i++){
				n += (arr[i]/mid);
			}
			
			if(n<N) {
				max = mid;
			} else {
				min = mid+1;
			}
		}
		
		sb.append(min-1);
		System.out.println(sb);		
			
	}

}
