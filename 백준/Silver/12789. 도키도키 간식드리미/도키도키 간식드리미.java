import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Stack<Integer> wait = new Stack<Integer>();
		int order = 1;
		String answer = "Nice";
		for(int i=0; i<N; i++) {
			if(arr[i] != order) {
				if(!wait.isEmpty() && wait.peek() == order) {
					wait.pop();
					i--;
					order++;
				} else {
					wait.push(arr[i]);
				}
			}
			else {
				order++;
			}
		}
		
		for(int i=0; i<wait.size(); i++) {
			int student = wait.pop();
			if(student == order) {
				order++;
			}
			else {
				answer = "Sad";
				break;
			}
		}
		
		bw.write(answer);
		bw.flush();
		bw.close();
		
	}

}