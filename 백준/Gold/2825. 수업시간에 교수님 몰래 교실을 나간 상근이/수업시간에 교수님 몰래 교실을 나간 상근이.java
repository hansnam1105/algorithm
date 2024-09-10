/**
 * 상근이 몰래 튐 2825
 * 정수 N 개 주어짐
 * 친구의 개수를 구해라
 * 친구란? 두 수를 이루는 숫자가 적어도 하나 겹치는 쌍을 친구 (겹치는 위치 달라도 됨)
 */

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        // 어차피 숫자는 0~9, 32면 00110...
        int[] friends = new int[1<<10];
        

        // 친구 입력 받아 비트마스킹 처리
        for (int i = 0; i < N; i++) {
            String friend = br.readLine();
            int bitmask = 0;
            for (int j=0; j<friend.length(); j++) {
                bitmask |= 1 << (friend.charAt(j) - '0');
            }
            friends[bitmask]++;
        }

        long totalPairs = 0;

        // 같은 마스크 가진 쌍 (친구) 계산 -> nC2
        for (int count : friends) {
            if (count > 1) { // 한 비트에 같은 숫자 2개 이상일경우
                totalPairs += (long) count * (count - 1) / 2;
            }
        }

        // 다른 마스크 끼리 비교 AND 연산 통해 0 아닐 경우 
        for (int i = 0; i < (1 << 10); i++) {
            for (int j = i + 1; j < (1 << 10); j++) {
                if ((i & j) != 0) {  // 공통 자릿수가 있을 경우
                    totalPairs += (long) friends[i] * friends[j];
                }
            }
        }

        bw.write(String.valueOf(totalPairs));
        bw.flush();
        bw.close();  
    }
}
