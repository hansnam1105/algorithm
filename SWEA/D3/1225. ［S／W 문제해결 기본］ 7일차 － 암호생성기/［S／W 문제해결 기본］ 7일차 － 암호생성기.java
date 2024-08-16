import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Queue<Integer> q;
		StringTokenizer st;
		
		
		for(int tc=1; tc<=10; tc++) {
			
			int T = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			q = new LinkedList<>();
			
			for (int i = 0; i < 8; i++) {
                q.offer(Integer.parseInt(st.nextToken())); // Queue에 숫자 8개 입력
            }
	
            int count = 1;

            while (true) {
            	
                int first = q.poll() - count++;
                if(count == 6)
                	count = 1;

                // 감소한 값이 0 이하일 경우
                if (first <= 0) {
                    q.offer(0);
                    break;
                }
                
                q.offer(first);
            }

            sb.append("#").append(T).append(" ");
            for (int i = 0; i < 8; i++) {
                sb.append(q.poll()).append(" ");
            }
            sb.append("\n");
		}
		System.out.println(sb);
	}

}
