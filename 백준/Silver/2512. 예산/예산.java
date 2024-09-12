/**
 * 모든 요청이 배정될 수 있는 경우에는 요청한 금액 그대ㄹ ㅗ배정
 * 상한액 오버시 상한액 배정 - 이를 구하라
 */

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int maxVal = Integer.parseInt(br.readLine());
		
		Arrays.sort(arr);
		int sum = 0;
		int left = 0, right = arr[N-1];
		while(left<=right) {
			int mid = (left+right) / 2;
			int budget = 0;
			for(int i=0; i<N; i++) {
				if(arr[i] > mid) {
					budget += mid;
				} else {
					budget += arr[i];
				}
			}
			if(budget > maxVal) {
				right = mid-1;
			}
			else {
				left = mid+1;
			}
		}
		bw.write(String.valueOf(right));
		bw.flush();
		bw.close();

	}

}
