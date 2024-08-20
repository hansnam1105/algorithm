/**
 * SWEA 1486 장훈이의 높은 선반
 * 이 문제를 그리디로 풀어보려고 하기 위해 아래와 같이 설계
 * Version 1.0
 * 1. Height를 내림차순 Sorting 한다
 * 2. Tower에 첫번째 Height를 더한다
 * 3. 탐욕 방식으로 그 다음 탑을 선택하는데 B-tower 보다 작거나 같은 값일 경우 빼도록했다
 * 4. 하지만 이럴 경우 B보다 큰 탑을 만들기 어려워 조건에 B-tower+Height[N](가장 Height 작은 값) 추가함
 * 5. 이 방식대로 할 경우 무수한 예외 처리를 해야해서 Ver 2.0으로 변환
 * 
 * Version 2.0 (Final)
 * 1. Height 오름차순 Sorting
 * 2. 부분 집합을 구해 부분집합의 합을 통한 Tower>=B의 값들을 구한다
 * 3. 부분 집합은 이전에 배운 비트마스킹을 활용해본다 1>>N -> 2^N 부분집합 개수
 * 4. 이 방식 활용 중 if((i & (1 << j)) != 0) 이조건을 생각하는데 GPT 도움을 조금 받았다
 * 5. 부분 집합의 합이 B보다 클 경우 최솟값 갱신
 */

import java.util.*;
import java.io.*;
import static java.lang.Integer.parseInt;


public class Solution {
	
	static int N, B;
	static int[] height;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = parseInt(st.nextToken());
			B = parseInt(st.nextToken());
			height = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                height[i] = parseInt(st.nextToken());
            }

            // 높이를 오름차순으로 정렬
            Arrays.sort(height);
            int minDiff = Integer.MAX_VALUE;
            
            // 모든 부분집합의 합을 구해본다
            for(int i = 0; i < (1 << N); i++) { // 1<<N -> 2^N 즉 모든 부분집합
                int sum = 0;
                for(int j = 0; j < N; j++) { 
                    if((i & (1 << j)) != 0) { // 부분집합에 있는 숫자 일시 합
                        sum += height[j];
                    }
                }
                // B 이상인 경우에 최소 차이를 갱신
                if(sum >= B) {
                    minDiff = Math.min(minDiff, sum - B);
                }
            }
			sb.append("#").append(tc).append(" ").append(minDiff).append("\n");
		}
		System.out.println(sb);
	}

}
