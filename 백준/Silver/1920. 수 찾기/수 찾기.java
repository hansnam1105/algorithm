/**
 * 수 찾기
 * 1920
 * 
 *
 */

import java.io.*;
import java.util.*;

public class Main {

	static int[] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			bw.write(binarySearch(Integer.parseInt(st.nextToken())) ? "1" : "0");
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
	
	static boolean binarySearch(int num) {
		int left = 0, right = arr.length-1;
		
		while(left<=right) {
			int mid = (left+right) / 2;
			if(arr[mid]>num) {
				right = mid-1;
			} else {
				left = mid+1;
			}
			
			if(arr[mid] == num) {
				return true;
			}
		}
		return false;
				
	}

}
