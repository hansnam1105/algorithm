/**
 * L초의 안내멘트가 끝난뒤 5초 동안 D초 마다 상담원 연결 요청 맞물리면 상담원 연결됨
 * N 번 L초의 안내멘트가 재생되면 상담원 연결
 * 상담원 연결 가장 빠른 시간 출력
 * 
 * 첫 안내 멘트는 0초 연결 요청은 D초에 1번씩
 * 안내 멘트 재생, 끝나는 시간에는 요청 불가
 */

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main {
	
	
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N, L, D;
		
		N = parseInt(st.nextToken());
		L = parseInt(st.nextToken());
		D = parseInt(st.nextToken());
		
		int totalTime = N * (L + 5);
		int time = 0;
		while(true) {
			/*
			 * 안내 멘트 재생 
			 */
			            
            if(time % (L+5) >= L){
                if(time % D == 0){
                    break;
                }
            }
            
            if(time/(L+5) >= N) {
                if(time % (D) == 0) {
                    break;
                }
            }
            
			time++;
		}
		sb.append(time);
		System.out.println(time);
		

	}

}
