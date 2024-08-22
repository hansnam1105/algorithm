/** 
 * 동전0
 * 그리디 알고리즘
 */

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = parseInt(st.nextToken());
		int K = parseInt(st.nextToken());
		int[] coin = new int[N];
		for(int i=0; i<N; i++) {
			coin[i] =parseInt(br.readLine());
		}
		int count = 0;
		while(K>0) {
			for(int i=N-1; i>=0; i--) {
				if(coin[i]<=K) {
					K -= coin[i];
					count++;
					break;
				}
				
			}
		}
		bw.write(String.valueOf(count));
		bw.flush();
		bw.close();
		br.close();
	}

}
