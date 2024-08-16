import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int M = Integer.parseInt(br.readLine());
		
		String[][] arr = new String[M][2];
		int S = 0;
		String str;
		int num;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			str = st.nextToken();
			if(str.equals("all")) {
				S = (1<<21)-1;
			}
			else if(str.equals("empty")) {
				S = 0;
			}
			else {
				num = Integer.parseInt(st.nextToken());
				if(str.equals("add")) {
					S |= (1<<num);				
				}
				else if(str.equals("check")) {
					sb.append((S&(1<<num)) != 0? 1 : 0).append("\n");
				}
				else if(str.equals("remove")) {
					S &= ~(1<<num);
				}
				else if(str.equals("toggle")) {
					S ^= (1<<num);
				}
			}	
		}
		System.out.println(sb);
	}

}
