import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int count = 1;
		while(true) {
			if(B == A) {
				bw.write(String.valueOf(count));
				break;
			}
			if(B < A || (B%10 != 1 && B % 2 != 0)) {
				bw.write("-1");
				break;
			}
			
			if(B % 10 == 1) {
				B /= 10;
				count++;
			}
			else if(B % 2 == 0) {
				B /= 2;
				count++;
			}
			
			
		}
		
		bw.flush();
		bw.close();
	}

}
