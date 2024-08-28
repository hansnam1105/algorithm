import java.util.*;
import java.io.*;

public class Main {
	
	static int L, C;
	static char[] arr;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[C];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<C; i++) {
			arr[i] = st.nextToken().charAt(0);
		}		
		Arrays.sort(arr);
		
		comb(0, new char[L], 0);
		
		bw.flush();
		bw.close();
		
	}
	
	public static void comb(int startIdx, char[] selected, int select) throws IOException {
		if(select == L) {
			if(valid(selected)) {
				bw.write(String.valueOf(selected));
				bw.append("\n");
			}
            return;
			
		}
		for(int i=startIdx; i<C; i++) {
			selected[select] = arr[i];
			comb(i+1, selected, select+1);
		}
	}
	
	public static boolean valid(char[] selected) {
		int mo = 0;
		int ja = 0;
		
		for(char c : selected) {
			if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
				mo++;
			}
			else {
				ja++;
			}
		}
		if(mo>=1 && ja>=2) {
			return true;
		}
		return false;
	}

}
