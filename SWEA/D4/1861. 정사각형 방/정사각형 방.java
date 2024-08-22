import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Solution {
	
	static int[][] map;
	static boolean[][] visited;
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
        			visited = new boolean[N][N];
        			bfs(i,j);
        		}
        	}
        	
        	sb.append("#").append(tc).append(" ").append(map[ansY][ansX]).append(" ").append(maxVal).append("\n");
        }
       
        System.out.println(sb);
	}
	
	public static void bfs(int y, int x) {
		Queue<Integer[]> q = new LinkedList<>();
		visited[y][x] = true;
		q.add(new Integer[] {y, x, 1});
		
		while(!q.isEmpty()) {
			Integer[] temp = q.poll();
			int cy = temp[0];
			int cx = temp[1];
			int count = temp[2];
			
			for(int i=0; i<4; i++) {
				int ny = cy + dy[i];
				int nx = cx + dx[i];
				
				if(check(ny,nx)) {
					if(map[ny][nx] == map[cy][cx] -1) {
						visited[ny][nx] = true;
						q.add(new Integer[] {ny, nx, count+1});
						if(count+1 > maxVal || (count+1 == maxVal && map[ny][nx] < map[ansY][ansX])) {
							maxVal = count+1;
							ansY = ny;
							ansX = nx;
							break;
						}

					}
				}
			}
		}
	}
	
	public static boolean check(int y, int x) {
		if(y<0 || y>=N || x<0 || x>=N) {
			return false;
		}
		else if(visited[y][x] == true) {
			return false;
		}
		return true;
	}

}
