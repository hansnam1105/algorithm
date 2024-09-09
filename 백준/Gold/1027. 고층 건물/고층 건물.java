/**
 * 고층 건물
 * 가장 많은 고층 빌딩이 보이는 고층 빌등을 찾는다
 * 빌딩은 총 N 개 
 * 가장 많은 빌딩이 보이는 빌딩을 구하고 
 * 거기서 보이는 빌딩의 수 출력
 * 두 건물 A 와 B의 지붕 을 잇는 선분 - 기울기
 * A와 B 사이 선분이 다른 고층 빌딩 안지나가게
 * 선분의 공식은 기울기 * x + A 건물 높이
 */

import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] buildings;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        buildings = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            buildings[i] = Integer.parseInt(st.nextToken());
        }
        int[] count = new int[N];
        for(int i=0; i<N-1; i++) {
        	for(int j=i+1; j<N; j++) {
        		if(calcAngle(i,j)) {
        			// i와 j 건물이 서로 보인다면,
                    // i번 건물에서 보이는 건물 수 증가
                    count[i]++;

                    // j번 건물에서 보이는 건물 수 증가
                    count[j]++;
        		}
        	}
        }
        
        // 최댓값 찾기
        int maxCount = 0;
        for (int i = 0; i < N; i++) {
            if (count[i] > maxCount) {
                maxCount = count[i];
            }
        }

        bw.write(String.valueOf(maxCount));
        bw.flush();
        bw.close();
    }

    static boolean calcAngle(int A, int B) {
    	double limit = 0;
    	int distance = B - A;
    	int height = buildings[B] - buildings[A]; // 오른쪽 탐색

    	double angle = (double) height / distance; //기울기

    	for (int i = 1; i < distance; i++) {      
    	    limit = angle * i + buildings[A]; // 기울기 * x + A건물 높이      
    	    if (limit <= buildings[A+i]) {            
    	        return false;                          
    	    }
    	}
    	return true;
    }
}


