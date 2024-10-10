import java.io.*;
import java.util.*;

/**
 * 건물 윤곽 스카이라인
 * 고도가 바뀌는 지점의 x y 좌표
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int answer = 0;
		Stack<Integer> stk = new Stack<>();
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			while(!stk.isEmpty() && stk.peek() > y) {
				answer++;
				stk.pop();
			} 
			if(!stk.isEmpty() && stk.peek() == y) {
				continue;
			}
			
			stk.push(y);

			
		}
		
		while(!stk.isEmpty()) {
			if(stk.peek() > 0) {
				answer++;
			}
			stk.pop();
		}
		
		System.out.println(answer);

	}

}
