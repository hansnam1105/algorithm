import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main {
	
	static int M, N, K; // M -> 행 N -> 열 K -> 영역
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static boolean[][] visited;
	static ArrayList<Integer> list; //영역 넓이 
	static int count; // 총 영역 개수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = parseInt(st.nextToken());
		N = parseInt(st.nextToken());
		K = parseInt(st.nextToken());
		
		map = new int[M][N];
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			// (c,r) -> (r,c) 로 입력변환 (기존 0,2 는 2,0 으로)
			int c1 = parseInt(st.nextToken());
			int r1 = parseInt(st.nextToken());
			int c2 = parseInt(st.nextToken());
			int r2 = parseInt(st.nextToken());
			for(int r=r1; r<r2; r++) {
				for(int c=c1; c<c2; c++) {
					map[r][c] = 1;
				}
			}
		}		
		count = 0;
		list = new ArrayList<>();
		visited = new boolean[M][N];
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 0 && !visited[i][j]) {
					bfs(i,j);
				}
			}
			
		}
		
		list.sort(null);
		sb.append(count).append("\n");
		for(int i : list) {
			sb.append(i).append(" ");
		}
		
		System.out.println(sb);

	}
	
	public static void bfs(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {r, c});
		visited[r][c] = true;
		int landSize = 1;
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			for(int i=0; i<4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				if(check(nr, nc)) {
					landSize+=1;
					visited[nr][nc] = true;
					queue.offer(new int[] {nr, nc});
				}
			}
		}
		count+=1;
		list.add(landSize);
	}
	
	public static boolean check(int r, int c) {
		return (r>=0 && r<M && c>=0 && c<N && !visited[r][c] && map[r][c] == 0);
		
	}
}
