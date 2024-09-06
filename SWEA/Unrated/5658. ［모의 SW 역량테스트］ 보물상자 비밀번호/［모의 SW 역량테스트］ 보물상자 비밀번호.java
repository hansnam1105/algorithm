import java.io.*;
import java.util.*;

public class Solution {

	static int N, K;
	static char[][] password;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			password = new char[4][N/4];
			String temp = br.readLine();
			for(int i=0; i<4; i++) {
				for(int j=0; j<N/4; j++) {
					password[i][j] = temp.charAt((i*N/4) + j);
				}
			}
			StringBuilder pw = new StringBuilder();
			TreeSet<String> pwSet = new TreeSet<>();
			for(int r=0; r<N/4; r++) {
				for(int i=0; i<4; i++) {
					for(int j=0; j<N/4; j++) {
						pw.append(password[i][j]);
					}
					pwSet.add(pw.toString());
					pw = new StringBuilder();
				}
				rotate();
			}
			
			String answer = "0";
			for(int i=0; i<K; i++) {
				answer = pwSet.pollLast();
			}
			int result = Integer.parseInt(answer, 16);
			bw.write("#" + tc + " " + result);
			bw.newLine();
			
		}
		bw.flush();
		bw.close();
		
	}
	
	static void rotate() {
	    char[] tmp = new char[4];

	    for(int i = 0; i < 4; i++) {
	        tmp[i] = password[i][N/4 - 1];
	    }

		for(int i = 0; i < 4; i++) {
		    for(int j = N/4 - 1; j > 0; j--) {
		        password[i][j] = password[i][j - 1];
		    }
		}
	
		password[0][0] = tmp[3];
		for(int i = 1; i < 4; i++) {
		    password[i][0] = tmp[i - 1];
		}
	}

}
