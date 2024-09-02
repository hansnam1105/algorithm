import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		
		// C가 홀수일 때만 A와 B를 한 번 XOR하면 되고, C가 짝수일 때는 그냥 A를 그대로 출력
		if (C % 2 != 0) {
            A ^= B;
        }
		
		bw.write(String.valueOf(A));
		bw.flush();
		bw.close();
		br.close();
	}

}
