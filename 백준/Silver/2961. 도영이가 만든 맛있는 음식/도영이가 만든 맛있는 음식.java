/**
 * 도영이가 만든 맛있는 음식
 * 
 */

import java.util.*;
import java.io.*;

public class Main {

	static int N, minVal;
	static int[][] arr;
	static int[] sList, bList;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][2];
		int sour, bitter;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			sour = Integer.parseInt(st.nextToken());
			bitter = Integer.parseInt(st.nextToken());
			arr[i][0] = sour;
			arr[i][1] = bitter;
		}
		
		minVal = 1000000000;
		sList = new int[N];
		bList = new int[N];
		
		cook(0,0);
		
		bw.write(String.valueOf(minVal));
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void cook(int startIdx, int toSelect) {
		if(toSelect>0) {
			int sTotal = 1, bTotal = 0;
			for(int i=0; i<toSelect; i++) {
				sTotal *= sList[i];
				bTotal += bList[i];
			}
			minVal = Math.min(minVal, Math.abs(sTotal - bTotal));
		}
		for(int i=startIdx; i<N; i++) {
			sList[toSelect] = arr[i][0];
			bList[toSelect] = arr[i][1];
			cook(i+1, toSelect+1);
			sList[toSelect] = 0;
			bList[toSelect] = 0;
		}
	}

}
