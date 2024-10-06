import java.io.*;
import java.util.*;
import java.awt.Point;

/**
 * 참으로 문제가 많은 문제
 * 입력값에 trim을 안하면 runtime이 발생한다
 */

public class Solution {
	
	static int N;
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int maxScore;

	static ArrayList<Point>[] wormhole;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			// trim 안하면 런타임 에러 발생
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			maxScore = 0;
			wormhole = new ArrayList[5];
			for(int i=0; i<5; i++) {
				wormhole[i] = new ArrayList<>();
			}
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] >= 6) {
						wormhole[map[i][j]-6].add(new Point(i,j));
						
					}
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] != 0)
						continue;

					for (int d = 0; d < 4; d++) {
						maxScore = Math.max(maxScore, getScore(i, j, d));
					}
				}
			}
			
			sb.append("#" + tc).append(" ").append(maxScore).append("\n");

			
		}
		System.out.println(sb);
	}
	
	
	static int getScore(int r, int c, int dir) {
		int score = 0;
		int nr = r;
		int nc = c;
		
		while(true) {
			nr += dr[dir];
			nc += dc[dir];

			// 출발 위치 돌아올 시 종료
			if (nr == r && nc == c) {
			    return score;
			}

			
			if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
			    return score * 2 + 1;
			}
			
			switch (map[nr][nc]) {
			case -1 : // 블랙홀
				return score;
			case 1 : // 블록
			case 2 :
			case 3 :
			case 4 :
			case 5 :
				dir = changeDir(map[nr][nc], dir);
				if (dir == -1) {
					score *= 2;
					score++;
					return score;
				}
				else
					score++;
				break;
			case 6: // 웜홀
			case 7:
			case 8:
			case 9:
			case 10:
				Point np = teleport(new Point(nr, nc), map[nr][nc]);
				nr = np.x;
				nc = np.y;
				break;
			}
			
			
		}
				
	}
	
	static int changeDir(int block, int dir) {
		switch (block) {
        case 1:
            return (dir == 0 || dir == 3) ? -1 : (dir == 1 ? 3 : 0);
        case 2:
            return (dir == 1 || dir == 3) ? -1 : (dir == 0 ? 3 : 1);
        case 3:
            return (dir == 1 || dir == 2) ? -1 : (dir == 0 ? 2 : 1);
        case 4:
            return (dir == 0 || dir == 2) ? -1 : (dir == 3 ? 0 : 2);
        case 5:
            return -1;
		}
		
		return 4;
	}
	
	static Point teleport(Point p, int hole) {
		return wormhole[hole - 6].get(0).equals(p) ? wormhole[hole - 6].get(1) : wormhole[hole - 6].get(0);
	}
}
