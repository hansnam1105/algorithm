/**
 * 숫자카드2
 * 숫자 카드 N 개 
 * 정수 M 주어졌을떄 상근이가 몇개 가지고 있는가
 *
 */

import java.io.*;
import java.util.*;

public class Main {
	
	static int[] cards;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		cards = new int[N];
		for(int i=0; i<N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(cards);
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			int num = Integer.parseInt(st.nextToken());
			int count = upperBound(num) - lowerBound(num);
			bw.write(String.valueOf(count) + " ");
		}
		bw.flush();
		bw.close();
		
	}
	
	// 찾고자 하는 값이 처음으로 등장하는 위치를 반환
    static int lowerBound(int num) {
        int left = 0, right = cards.length;

        while (left < right) {
            int mid = (left + right) / 2;
            if (cards[mid] >= num) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    
    static int upperBound(int num) {
    	int left = 0, right = cards.length;
    	
    	while (left < right) {
    		int mid = (left + right) / 2;
    		if (cards[mid] > num) {
    			right = mid;
    		} else {
    			left = mid + 1;
    		}
    	}
    	return left;
    }

}
