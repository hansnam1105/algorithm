/**
 * 현주가 좋아하는 제곱근 놀이 6782
 * 2 이상의 어떤 정수 N
 * N -> N+1로 바꿀 수 있다
 * sqrt(N)이 정수일때, N->sqrt(N) 바꿀 수 있다
 * 
 * 게임 목표 N->2 만들기
 * 횟수 최솟값 구하기
 * 
 * N이 sqrt 되는지 체크 안되면 N+1
 * 
 * 1차시도 
 * Float으로 입력받아
 * 소수점 이하 값 존재시 +1 아니면 sqrt
 * 하지만 시간 초과 발생
 * 
 * 2차 시도
 * Long으로 입력받는다
 * temp = sqrt(N)으로 해서 temp * temp = N 일시 (long이라 sqrt(N)이 정수일시 True)
 * N = temp
 * else
 * N += 1
 */

import java.io.*;
import static java.lang.Integer.parseInt;


public class Solution {
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
       
        int T = parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
        	long N = Long.parseLong(br.readLine());
        	long answer = 0;
        	while(true) {
        		if(N==2) {
        			break;
        		}
        		long temp = (long) Math.sqrt(N);
        		if(temp * temp == N) {
        			N=temp;
        		}
        		else {
        			// temp * temp != N 이기 때문에
        			// (temp+1) * (temp+1)이 가장 가까운 sqrt(N)이 정수가 되는 경우다
        			answer += (temp+1) * (temp+1) - N;
        			N = temp+1;
        		}
        		answer++;
        	}
        	
        	sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        
        System.out.println(sb);
	}
	
}
