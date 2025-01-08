import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N, M, B;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        int min = 256;
		int max = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(min > map[i][j]) min = map[i][j];
				if(max < map[i][j]) max = map[i][j];
			}
		}
		int time = Integer.MAX_VALUE;
		int high = 0;
		for(int i = min; i <= max; i++) {
			int count = 0;
			int block = B;
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < M; k++) {
					if(i < map[j][k]) {
						count += ((map[j][k] - i) * 2);
						block += (map[j][k] - i);
					}else {
						count += (i - map[j][k]);
						block -= (i - map[j][k]);
					}
				}
			}
			if(block < 0) break;
			
			if(time >= count) {
				time = count;
				high = i;
			}
		}
		bw.write(time + " " + high);
        bw.flush();
        bw.close();
    }

}
