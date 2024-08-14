import java.util.*;
import java.io.*;
 
public class Solution {
	 
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		char[] parent= {'(', ')', '[', ']', '{', '}', '<', '>'};


		for(int T=1; T<=10; T++) {
			int tc = Integer.parseInt(br.readLine());
			String parentInput = br.readLine();
			char[] arr = new char[tc];
			Stack<Character> stk = new Stack<>(); 
			for(int i=0; i<tc; i++) {
				char cur = parentInput.charAt(i);
				if(cur == parent[1] && stk.peek().equals(parent[0])) {
					stk.pop();
				}
				else if(cur == parent[3] && stk.peek().equals(parent[2])) {
					stk.pop();
				}
				else if(cur == parent[5] && stk.peek().equals(parent[4])) {
					stk.pop();
				}
				else if(cur == parent[7] && stk.peek().equals(parent[6])) {
					stk.pop();
				} else {
					stk.push(cur);
				}
			}
			
			if(stk.isEmpty()) {
				sb.append("#").append(T).append(" 1\n");
			} else {
				sb.append("#").append(T).append(" 0\n");
			}
			
		}
        System.out.println(sb);
	}

}
