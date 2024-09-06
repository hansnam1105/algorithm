import java.io.*;
import java.util.*;

public class Main {
	
	static int N, C;
	static int[] houses;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		houses = new int[N];
		for(int i=0; i<N; i++) {
			houses[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(houses);
		
		int start = 1;
		int end = houses[N-1] - houses[0] + 1; //전체 집 거리
		
		while(start<end) {
			int mid = (start+end) / 2;
			
			if(canInstall(mid) < C) {
				end = mid;
			}
			else {
				start = mid+1;
			}
		}
		
		bw.write(String.valueOf(start-1));
		bw.flush();
		bw.close();
	}
	
	static int canInstall(int target) {
		int count = 1;
		int routerLoc = houses[0]; // 첫번째 집 무조건 설치
		
		for(int i=1; i<houses.length; i++) {
			int loc = houses[i];
			if((loc - routerLoc)>=target) {
				count++;
				routerLoc = loc;
			}
		}
		
		return count;
		
	}

}
