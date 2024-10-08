import java.io.*;
import java.util.*;

public class Main {	
	
	
	static char[] arr;
	static int count = 0;
	static int index;
	static String ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		while(true) {
			count = 0;
			String temp = br.readLine();
			if(temp == null || temp.equals("")) {
				break;
			}
			ans = "No permutation";
			
			StringTokenizer st = new StringTokenizer(temp);
			
			String N = st.nextToken();
			arr = new char[N.length()];
			index = Integer.parseInt(st.nextToken());
			for(int i=0; i<N.length(); i++) {
				arr[i] = N.charAt(i);
			}
			
			comb(new char[N.length()], 0, new boolean[N.length()]);
			
			bw.write(N + " " + index + " = " + ans + "\n");
		}
		
		bw.flush();
		bw.close();
		
		
	}
	
	static void comb(char[] word, int len, boolean[] visited) {
		if(word.length == len) {
			count++;
			if(count == index) {
				ans = new String(word);
			}
		}
		for(int i=0; i<word.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				word[len] = arr[i];
				comb(word, len+1, visited);
				visited[i] = false;
			}
		}
	}
	
}
