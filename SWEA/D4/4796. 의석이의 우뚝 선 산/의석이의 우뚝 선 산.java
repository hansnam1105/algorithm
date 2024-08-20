/**
 * SWEA 의석이의 우뚝 선 산 탐욕 알고리즘
 * 오르막 구간을 계산하고 내리막 구간을 계산한다
 * 만약 우뚝 선 상 성립시 우뚝 선산 구간 추가
 * 
 */

import java.util.*;

public class Solution {
    
    static int N;
    static int[] h;

    public static void main(String[] args) throws Exception {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = sc.nextInt();
        for(int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            h = new int[N];
            
            for(int i = 0; i < N; i++) {
                h[i] = sc.nextInt();
            }
            
            int count = 0;
            int i = 0;
            
            while(i < N - 1) {
                int up = 0;
                int down = 0;
                
                // 오르막 구간 계산
                while(i < N - 1 && h[i] < h[i + 1]) {
                    up++;
                    i++;
                }
                
                // 내리막 구간 계산
                while(i < N - 1 && h[i] > h[i + 1]) {
                    down++;
                    i++;
                }
                
                // "우뚝 선 산"이 성립하는 경우
                if(up > 0 && down > 0) {
                    count += up * down;
                }
                
                // 내리막이 끝나고 다음 구간으로 넘어가기 위해 i를 증가
                while(i < N - 1 && h[i] == h[i + 1]) {
                    i++;
                }
            }
            
            sb.append("#").append(tc).append(" ").append(count).append("\n");
        }
        
        System.out.println(sb);
    }
}
