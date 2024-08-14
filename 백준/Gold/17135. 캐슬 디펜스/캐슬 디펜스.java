/**
 * P17135
 * 캐슬 디펜스
 */

import java.util.*;
import java.io.*;

public class Main {

    static int N, M, D, max = 0;
    static int[][] map;
    // 3명의 궁수 배치 위치 저장 배열
    static int[] archLoc = new int[3];

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(in.readLine());
        

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
                
        archer(0,0);
        
        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();

    }
    
    // 궁수 배치 경우의 수를 만든다
    public static void archer(int start, int cnt) {
    	// 궁수 배치 후 게임 시작
    	if (cnt == 3) {
        	max = Math.max(max, game());
        	return;
        }

        for (int i = start; i < M; i++) {
        	archLoc[cnt] = i;
            archer(i + 1, cnt + 1);
        }
    }
    
    // 게임 시작
    public static int game() {
    	int totalKill = 0;
    	
    	int[][] gameMap = new int[N][M];
    	
    	for(int l=N; l>0; l--) { //N 번쨰 line 부터 탐색
    		for(int arch : archLoc) { // 각 궁수 마다
    			for(int dist=1; dist<=D; dist++) { //왼쪽 부터 탐색
    				int killCnt = attack(arch, l, dist, gameMap);
    				if(killCnt<0) {
    					continue;
    				}
    				totalKill+=killCnt;
    				break;
    			}
    		}
    	}

        return totalKill;
    }
    
    // 공격 경우의 수
    // distance = |r1-r2| + |c1-c2|
    // |r1-r2| = distance - |c1-c2|
    // r1 = distance - (r2-|c1-c2|)
    public static int attack(int arch, int line, int dist, int[][] gameMap) {
    	int nline;
    	for(int mline=arch-dist; mline<=arch+dist; mline++) { //사거리 왼쪽부터 오른쪽까지
    		nline = line - (dist-Math.abs(mline-arch));
    		if(checkOpt(nline, mline, line)) {
    			continue;
    		}
    		if(gameMap[nline][mline] == 0) {
    			gameMap[nline][mline] = line;
    			return 1;
    		} else if(gameMap[nline][mline] == line) {
    			return 0;
    		}
    	}
    	return -1;
    }
    
    // 나갈 경우
    public static boolean checkOpt(int nline, int mline, int line) {
    	if(nline<0 || nline>=line || mline<0 || mline>=M) {
			return true;
		}
		if(map[nline][mline] == 0) {
			return true;
		}
		return false;
    }
}
