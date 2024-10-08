/**
  * 1218. [S/W 문제해결 기본] 4일차 - 괄호 짝짓기 D4
  * 메모리 : 
  * 실행시간 : 
  * 
  * 스택을 활용한 괄호 짝짓기
  */

import java.util.*;
import java.io.*;
 
public class Solution {
	 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		// 괄호 확인 용 배열
		char[] parent= {'(', ')', '[', ']', '{', '}', '<', '>'};


		for(int T=1; T<=10; T++) {
			int tc = Integer.parseInt(br.readLine());
			String parentInput = br.readLine();
			Stack<Character> stk = new Stack<>(); 
			
			for(int i=0; i<tc; i++) {
				// 현재 입력받은 값
				char cur = parentInput.charAt(i);
				//괄호 존재시 pop
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