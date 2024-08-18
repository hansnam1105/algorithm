import java.util.*;
import java.io.*;

public class Main {

	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());		
		dfs(N, 0);
		
		System.out.println(sb);
		
	}
	
	public static void dfs(int N, int cur) {
		if(N==0) {
			if(isPrime(cur))
				sb.append(cur).append("\n");
			return;
		}
		for(int i=1; i<=9; i++) {
			int next = cur*10 + i;
			if(isPrime(next)) {
				dfs(N-1, next);
			}
		}
		
	}
	
	public static boolean isPrime(int num) {
		if(num<2) {
			return false;
		}
		for(int i=2; i<=Math.sqrt(num); i++) {
			if(num%i == 0) 
				return false;
		}
		return true;
	}

}
