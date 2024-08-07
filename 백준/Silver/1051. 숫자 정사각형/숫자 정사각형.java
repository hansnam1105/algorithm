import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			String row = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = row.charAt(j) -'0';
			}
		}
		int len = Math.min(N, M);
		while(len>1) {
			for(int i=0; i<=N-len; i++) {
				for(int j=0; j<=M-len; j++) {
					int num = map[i][j];
					if(num == map[i+len-1][j]
					&& num == map[i][j+len-1]
					&& num == map[i+len-1][j+len-1]) {
						int answer = len*len;
						bw.write(answer +"");
						bw.flush();
						bw.close();
						return;
					}
				}
			}
			len--;
		}
		int answer = len*len;
		bw.write(answer+ "");
		bw.flush();
		bw.close();
	}

}
