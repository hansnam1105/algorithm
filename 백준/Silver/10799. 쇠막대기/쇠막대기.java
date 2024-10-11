import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Stack<Character> stk = new Stack<>();
		int answer = 0;
		String temp = br.readLine();
		for(int i=0; i<temp.length(); i++) {
			char c = temp.charAt(i);
			if (c == '(') {
                stk.push(c);
            } 
			// ) 입력시
            else {
            	
                if (!stk.isEmpty() && temp.charAt(i-1) == '(') {
                    stk.pop();
                    answer += stk.size(); 
                } 
                else {
                    stk.pop();
                    answer++;
                }
            }
			
		}
		
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		
	}

}
