import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[] X = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			X[i] = Integer.parseInt(st.nextToken());
		}
		int[] sortX = X.clone();
		Arrays.sort(sortX);
		HashMap<Integer, Integer> sortMap = new HashMap<Integer, Integer>();

		int index = 0;
		for(int i : sortX) {
			if(!sortMap.containsKey(i)) {
				sortMap.put(i, index++);
			}
		}
		StringBuilder sb= new StringBuilder();
		for(int i : X) {
			sb.append(sortMap.get(i)).append(' ');
		}
        System.out.println(sb);
	}
}

