/**
 * 블루레이에는 총 N 개의 강의
 * i 번 강의와 j 번 강의를 녹화하려면 i~j 사이 모든 강의 같은 블루레이 녹화
 * 블루레이 개수를 줄이려고 한다
 * M 개의 블루레이에 모든 영상 녹화 - 크기를 최소화
 * M 개의 블루레이는 모두 같은 크기
 * 
 * 블루레이 최소 크기와 최대 크기를 설정해서 줄여 나간다
 * 최소 크기는 가장 긴 강의 최대 크기는 모든 강의 길이의 합
 * 이진 탑색을 통해 중간값 가정 값 생성
 * 몇 개의 블루레이 필요한지 계산
 * 블루레이 개수 M 보다 크면 블루레이 크기를 늘려야 하므로 start = mid +1;
 * M 보다 작거나 같으면 end 값을 줄인다
 */

import java.io.*;
import java.util.*;

public class Main {
	
	static int[] lectures;
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		lectures = new int[N];
		st = new StringTokenizer(br.readLine());
		int sum = 0;
		int maxBlueray = 0;
		for(int i=0; i<N; i++) {
			lectures[i] = Integer.parseInt(st.nextToken());
			sum += lectures[i];
			if(maxBlueray < lectures[i]) {
				maxBlueray = lectures[i];
			}
		}
		
		bw.write(String.valueOf(binarySearch(maxBlueray, sum, M)));
		bw.flush();
		bw.close();

	}

	static int binarySearch(int start, int end, int target) {
		while(start < end) {
			int mid = (start + end) / 2;
			if(getCount(mid) > target) {
				start = mid + 1;
			}
			else {
				end = mid;
			}
			
		}
		return start;

	}
	
	static int getCount(int mid) {
		int count = 1;
		int remain = mid;
		for(int i=0; i<N; i++) {
			if(remain <lectures[i]) {
				remain = mid;
				count++;
			}
			remain -= lectures[i];
			
		}
		
		return count;
	}
}
