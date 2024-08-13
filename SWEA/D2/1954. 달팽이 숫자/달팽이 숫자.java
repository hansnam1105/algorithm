import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
        // 좌 하 우 상 탐색
		int[] dx = {0, 1, 0, -1};
		int[] dy = {1, 0, -1, 0};
		
		for(int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			int cx = 0;
			int cy = 0;
            // 0,0 은 1부터 시작
			map[0][0] = 1;
			for(int i=2; i<=N*N; i++) {
				for(int j=0; j<4; j++) {
					int nx = cx + dx[j];
					int ny = cy + dy[j];
                    //배열 범위 밖으로 안나가게
					if(nx>=0 && nx<N && ny>=0 && ny<N) {
                        // 다음 칸이 0인 경우 이동
						if(map[nx][ny] == 0) {
							map[nx][ny] = i;
							cy = ny;
							cx = nx;
							break;
						}
					}
				}
			}
			sb.append("#" + tc + "\n");
			
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					sb.append(map[i][j] + " ");
				}
				sb.append("\n");
			}
			
		}
		
		System.out.println(sb);

	}

}
