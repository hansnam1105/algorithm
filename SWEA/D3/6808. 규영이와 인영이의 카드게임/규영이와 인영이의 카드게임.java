/**
 * 순열 문제
 * 
 */

import java.util.*;
import java.io.*;

public class Solution {

	static int Cards = 18, Player = 9; // 18장의 카드 플레이어 9장 카드						
	static int[] gyu; // 규영
	static int[] in; // 인영
	static int[] inCards; //인영이 낼 수 있는 카드
	static boolean[] isSelected;
	static int win, lose;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			// 승패 초기화 및 배열 초기화
			win = 0;
			lose = 0;
			gyu = new int[Player];
			in = new int[Player];
			isSelected = new boolean[Player];
			inCards = new int[Player];
			
			boolean[] totalCards = new boolean[Cards]; // 모든 카드 숫자
			int idx = 0;

			StringTokenizer st = new StringTokenizer(br.readLine());			
			// 규영이 카드 추가 및 전체 카드 배열에서 카드 사용되었음 적용
			for (int i=0; i<Player; i++) {
				gyu[i] = Integer.parseInt(st.nextToken());
				totalCards[gyu[i]-1] = true;
			}
			
			// 사용되지 않은 카드를 인영이가 쓸수 있는 카드로 사용 가능하게 적용
			for(int i = 0; i<Cards; i++) {
				if(!totalCards[i])
					inCards[idx++] = i+1;
			}
			
			game(0);
			
			sb.append("#").append(tc).append(" ").append(win).append(" ").append(lose).append("\n");
		}
		System.out.println(sb);
	}
	
	public static void game(int count) {
		if(count == Player) {
			int gPoint = 0;
			int iPoint = 0;
			for(int i=0; i<Player; i++) {
				if(gyu[i] > in[i]) {
					gPoint += (gyu[i]+in[i]);
				}
				else {
					iPoint += (gyu[i]+in[i]);
				}
			}
			if(gPoint > iPoint) {
				win++;
			} else if(iPoint > gPoint) {
				lose++;
			}
		}
		
		// 순열 탐색
		for(int i=0; i<Player; i++) {
			if(!isSelected[i]) {
				in[count] = inCards[i];
				isSelected[i] = true;
				game(count+1);
				isSelected[i] = false;
			}
		}
	}
}
