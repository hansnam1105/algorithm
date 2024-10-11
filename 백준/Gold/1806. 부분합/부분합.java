import java.io.*;
import java.util.*;

public class Main {

	static int N, S;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int sum = 0;		
		int start = 0;
		int end = 0;
		int minLength = 100001;
		while (end < N) {
            sum += arr[end++];

            while (sum >= S) {
                minLength = Math.min(minLength, end - start);
                sum -= arr[start++];
            }
        }

        if (minLength == 100001) {
            bw.write("0");
        } else {
            bw.write(String.valueOf(minLength));
        }
		bw.flush();
		bw.close();
		

	}

}
