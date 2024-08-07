import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2468번 안전 영역
 */
public class Main {
	
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	static int N;
	static int[][] map;
	static boolean[][] visited;

	public static int dfs(int x, int y, int rain) {
		if (x < 0 || y < 0 || x >= N || y >= N) {
            return 0;
        }
		
		if (!visited[x][y] && map[x][y] > rain) {
			visited[x][y] = true;
			
			for(int i=0; i<4; i++) {
				dfs(x+dx[i], y+dy[i], rain);
			}
		}
		
		return 1;
            
	}
	
    public static void main(String[] args) throws NumberFormatException, IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        int maxVal = 0;
        int minVal = 100;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]>maxVal) maxVal = map[i][j];
                if(map[i][j]<minVal) minVal = map[i][j];
            }
        }
        int answer = 0;
        if(maxVal == minVal) {
        	System.out.println(1);
        }
        else {
	        for(int rain=minVal-1; rain<maxVal; rain++) {
	        	visited = new boolean[N][N];
	        	int count = 0;
	        	for(int i=0; i<N; i++) {
	        		for(int j=0; j<N; j++) {
	        			if(!visited[i][j] && map[i][j]>rain) {
	        				count += dfs(i, j, rain);
	        			}
	        		}
	        	}
	        	answer = Math.max(answer, count);
	        	
	        }
	        
	        System.out.println(answer);
        }

    }

}