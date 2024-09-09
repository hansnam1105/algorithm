
import java.io.*;
import java.util.*;

public class Main {

	static int N, maxVal;
	static int[] arr;
	static ArrayList<Integer> lis;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		lis = new ArrayList<>();
		
		
		for(int i=0; i<N; i++) {
			if(lis.isEmpty() || lis.get(lis.size()-1) < arr[i]) {
				lis.add(arr[i]);
			}
			else {
				int idx = binarySearch(0, lis.size()-1, arr[i]);
				lis.set(idx, arr[i]);
			}
		}
		bw.write(String.valueOf(lis.size()));
		bw.flush();
		bw.close();
	}
	
	static int binarySearch(int left, int right, int target) {
		
		while (left < right) {
			int mid = (left + right) / 2;
			if (lis.get(mid) < target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return right;
	}

}
