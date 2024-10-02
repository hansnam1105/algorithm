import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int gearSize = 8; // 기어의 이빨 수
        int numberOfGears = 4; // 기어의 개수
        int K; // 회전 횟수
        int T = Integer.parseInt(br.readLine());
        
        for(int tc=1; tc<=T; tc++) {
        	int[] gears = new int[numberOfGears];
        	K = Integer.parseInt(br.readLine()); // 회전 횟수 입력
        	
        	// 초기 기어 상태 입력
            for (int i = 0; i < numberOfGears; i++) {
                st = new StringTokenizer(br.readLine());
                int gearBits = 0;
                for (int j = 0; j < gearSize; j++) {
                    if (st.nextToken().equals("1")) {
                        gearBits |= (1 << (gearSize - 1 - j)); // 비트를 설정
                    }
                }
                gears[i] = gearBits; // 각 기어의 위치 저장
            }

            for (int i = 0; i < K; i++) {
            	st = new StringTokenizer(br.readLine());
                int gearNum = Integer.parseInt(st.nextToken()) - 1;
                int dir = Integer.parseInt(st.nextToken()); 

                int[] rotation = new int[numberOfGears];
                rotation[gearNum] = dir;

                // 왼쪽 기어 회전 여부 체크
                for (int j = gearNum; j > 0; j--) {
                    int rightGear = gears[j - 1];
                    int leftGear = gears[j];

                    // 오른쪽 5번 왼쪽 1번
                    int rightPole = (rightGear >> 5) & 1;
                    int leftPole = (leftGear >> 1) & 1;

                    if (rightPole != leftPole) {
                        rotation[j-1] = -rotation[j];
                    } else {
                        break;
                    }
                }

                // 오른쪽 기어 회전 여부 체크
                for (int j = gearNum; j < numberOfGears-1; j++) {
                    int leftGear = gears[j + 1];
                    int rightGear = gears[j];

                    // 왼쪽 1번 오른쪽 5번
                    int leftPole = (leftGear & (1 << 1)) > 0 ? 1 : 0;
                    int rightPole = (rightGear & (1 << 5)) > 0 ? 1 : 0;

                    if (leftPole != rightPole) {
                        rotation[j+1] = -rotation[j];
                    } else {
                        break;
                    }
                }

             // 기어 회전 적용
                for (int j = 0; j < numberOfGears; j++) {
                    if (rotation[j] == 0) continue;

                    if (rotation[j] == 1) { // 시계 방향
                    	if((gears[j] & 1) > 0) {
                        	gears[j] >>>= 1;
                        	gears[j] |= (1<<7);
                        } else {
                        	gears[j] >>>= 1;
                        }
                    } else if (rotation[j] == -1) { // 반시계 방향
                        if((gears[j] & (1<<7)) > 0) {
                        	gears[j] <<= 1;
                        	gears[j] |= 1;
                        } else {
                        	gears[j] <<= 1;
                        }
                    }
                }
              
            }

            // 최종 점수 계산
            int score = 0;
            score += (gears[0] & (1<<7)) == 0 ? 0 : 1;
            score += (gears[1] & (1<<7)) == 0 ? 0 : 2;
            score += (gears[2] & (1<<7)) == 0 ? 0 : 4;
            score += (gears[3] & (1<<7)) == 0 ? 0 : 8;

            bw.write("#" + tc + " " + score);
            bw.newLine();
        }
        
        bw.flush();
        bw.close();     
        
    }
}
