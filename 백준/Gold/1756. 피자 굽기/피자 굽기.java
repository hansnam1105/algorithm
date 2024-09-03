/**
 * P1756
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int D = Integer.parseInt(st.nextToken());
    	int N = Integer.parseInt(st.nextToken());
    	
    	// 오븐 새로 만들기 최댓값으로
    	int[] oven = new int[D+1];
    	st = new StringTokenizer(br.readLine());
    	for(int i=1; i<=D; i++) {
    		oven[i] = Integer.parseInt(st.nextToken());
    	}
    	for(int i=2; i<=D; i++) {
    		oven[i] = Math.min(oven[i], oven[i-1]);
    	}
    	// 피자 오븐에 넣기
    	int pizzaSize = 0;
    	int depth = D;
    	st = new StringTokenizer(br.readLine());
    	for(int i=0; i<N; i++) {
    		pizzaSize = Integer.parseInt(st.nextToken());
    		
    		while(depth > 0 && oven[depth] < pizzaSize) {
    			depth--;
    		}
    		if(depth == 0) {
        		bw.write("0");
        		bw.flush();
        		bw.close();
        		return;
        	}
    		//피자 넣고 한칸 위로
    		depth--;
    	}    	
    	
    	bw.write(String.valueOf(depth+1));
    	bw.flush();
    	bw.close();
    	

    }
    
}