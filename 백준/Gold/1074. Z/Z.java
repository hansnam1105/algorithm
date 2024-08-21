import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main {
	
	static int ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = parseInt(st.nextToken());
		int r = parseInt(st.nextToken());
		int c = parseInt(st.nextToken());
		
		int mapLength = (int) Math.pow(2, N);
				
		searchSpace(mapLength, r, c);
		
		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void searchSpace(int n, int r, int c) {
		int size = n/2;
		// 2 3 1
		
		if(n == 1) {
			return;
		} else {
		    
			
			// 사분면 위치
			// 2 3 1 일시 3>1 1
			// size = 1
			// searchSpace(1, 1, 1)
			// ans += 8
			// ans += 3
			if(r<size && c<size) {
				searchSpace(size, r, c);
			}
			else if(r<size && c>=size) {
				ans += n*n/4;
				searchSpace(size, r, c-size);
			}
			else if(r>=size && c<size) {
				ans += 2*(n*n/4);
				searchSpace(size, r-size, c);
			}
			else if(r>=size && c>=size) {
				ans += 3*(n*n/4);
				searchSpace(size, r-size, c-size);
			}
			
			
			
		}
	}
	
	
	
	

}
