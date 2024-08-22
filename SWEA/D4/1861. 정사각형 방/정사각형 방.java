import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Solution {
	
	static int[][] map;
	static int[][] dp;  // 각 지점에서 시작하는 최대 경로 길이를 저장할 배열 (visited 대신 이걸 사용)
	static int N, maxVal;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static int ansY, ansX;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        
        for(int tc = 1; tc <= T; tc++) {
        	N = Integer.parseInt(br.readLine());
        	map = new int[N][N];
            dp = new int[N][N];
        	maxVal = 0;
        	for(int i=0; i<N; i++) {
        		st = new StringTokenizer(br.readLine());
        		for(int j=0; j<N; j++) {
        			map[i][j] = parseInt(st.nextToken());
        		}
        	}
        	ansY = 0;
			ansX = 0;
			for(int i=0; i<N; i++) {
        		for(int j=0; j<N; j++) {
        			 int length = dfs(i,j);
        			 if(length > maxVal || (length == maxVal && map[i][j] < map[ansY][ansX])) {
        				 maxVal = length;
        				 ansY = i;
        				 ansX = j;
        			 }
        		}
        	}
			
			sb.append("#").append(tc).append(" ").append(map[ansY][ansX]).append(" ").append(maxVal).append("\n");
        }
		System.out.println(sb);
	}
	
	public static int dfs(int y, int x) {
		// 이미 계산된 경우
		if(dp[y][x] != 0) {
			return dp[y][x];
		}

		dp[y][x] = 1;  // 최소 경로 길이 1로 시작 (자기 자신)
		for(int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];

			if(ny >= 0 && ny < N && nx >= 0 && nx < N && map[ny][nx] == map[y][x] + 1) {
				dp[y][x] = Math.max(dp[y][x], dfs(ny, nx) + 1);
			}
		}

		return dp[y][x];
	}

}