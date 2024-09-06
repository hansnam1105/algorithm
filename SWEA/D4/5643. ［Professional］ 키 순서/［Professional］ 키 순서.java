/**
 * 키 순서 문제
 * 그래프 탐색으로 
 * 한 학생마다 가 수 있는 모든 정점을 탐색해서
 * 
 * 메모이제이션 활용
 * 중복된 탐색을 하지 않도록 최적화
 */

import java.io.*;
import java.util.*;

public class Solution {

    static int N, M;
    static int[][] adjMatrix;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        
        for(int tc=1; tc<=T; tc++){
            


        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        adjMatrix = new int[N+1][N+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjMatrix[a][b] = 1;
        }
        
        for(int i=1; i<=N; i++) {
        	adjMatrix[i][0] = -1; // 탐색되지 않은 학생을 나타낸다(후에 탐색이 완료되면 자신보다 큰 학생 수 저장)
        }
        
        for(int i=1; i<=N; i++) {
        	if(adjMatrix[i][0] != -1) continue;
        	dfs(i);
        }
        
        for(int i=1; i<=N; i++) {
        	for(int j=1; j<=N; j++) {
        		adjMatrix[0][j] += adjMatrix[i][j];
        	}
        }
        
        int ans = 0;
        for(int k=1; k<=N; k++) {
        	if(adjMatrix[k][0] + adjMatrix[0][k] == N-1) ans++;
        }

        
        bw.write("#" + tc + " " + String.valueOf(ans) + "\n");
        }

        bw.flush();
        bw.close();

    }

    static void dfs(int cur) {
        for(int i=1; i<=N; i++) {
        	if(adjMatrix[cur][i] == 0) continue;
        	if(adjMatrix[i][0] == -1) { // 탐색되지 않은 학생이므로 탐색
        		dfs(i);
        	}
        	
        	// 나보다 키가 큰 학생이 탐색을 완료한 상태
        	// i 보다 키가 큰 학생이 있다면 그 학생들의 정보를 cur에게 반영(간접 관계 직접관계로 경로압축)
        	if(adjMatrix[i][0]>0) {
        		for(int j=1; j<=N; j++) {
        			if(adjMatrix[i][j] != 0) adjMatrix[cur][j] = 1;
        		}
        	}
        }
        
        adjMatrix[cur][0] = 0; // 초기값이 -1이므로 누적위해 0으로 초기화
        for(int k=1; k<=N; k++) {
        	adjMatrix[cur][0] += adjMatrix[cur][k];
        }
        return;
    }

}
