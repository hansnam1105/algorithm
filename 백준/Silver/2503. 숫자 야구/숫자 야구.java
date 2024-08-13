import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		boolean[] check = new boolean[988];
		
		for(int i=123; i<= 987; i++) {
    		String num = String.valueOf(i);
    		
    		if(num.charAt(0) == '0' || num.charAt(1) == '0' || num.charAt(2) == '0') continue; 
    		if(num.charAt(0) == num.charAt(1) || num.charAt(0) == num.charAt(2) || num.charAt(1) == num.charAt(2)) continue;
    	    check[i] = true;	
    	}
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc<T; tc++) {
			st = new StringTokenizer(br.readLine());
			String game = st.nextToken();
			int strike = Integer.parseInt(st.nextToken());
			int ball = Integer.parseInt(st.nextToken());
			
			for(int i=123; i<=987; i++) {
				if(check[i]) {
					int sc = 0;
					int bc = 0;
					
    				for(int one=0; one<3; one++) {
    					char game_split = game.charAt(one);
    					
    					for(int two=0; two<3; two++) {
    						char guess = Integer.toString(i).charAt(two);
    						
    						if(game_split == guess && one == two) sc++;
    						else if(game_split == guess && one != two) bc++;
    					}
    					if(sc == strike && bc == ball) {
    						check[i] = true;
    					}
        				else {
        					check[i] = false;
        				}
        				
        			}
    			
				}
			}
			
		}
		int result = 0;
    	for(int i=123; i<=987; i++) {
    		if(check[i]) result++;
    	}
    	System.out.println(result);
		
	}

}
