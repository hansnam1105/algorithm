import java.io.*;
import java.util.*;
import java.awt.Point;

/**
 * 이게 뭔소리여
 * 벽 부수고 이동할 수 있는 곳으로 변경
 * 그 위치에서 이동할 수 있는 칸 의 개수
 * 101    001    301 
 * 010 -> 010 -> 010
 * 101    101    101
 * 인접한 0의 개수 채워 넣기
 * 
 * 연결된 빈칸(0) 그룹화
 * 그룹에 고유 번호 (섬 다리 연결하는 문제처럼)
 * 각 벽 칸(1)에 상하좌우 빈칸 그룹 번호 수집
 * set으로 중복 합산 x
 */

public class Main {
	
	static int N, M, minZone;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static char[][] map;
	static int[][] group; // 빈칸 그룹 저장
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		group = new int[N][M];
		visited = new boolean[N][M];
		
		
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		int groupId = 1;
		List<Integer> list = new ArrayList<>(); // 그룹 크기 저장 리스트
		list.add(0);
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(!visited[i][j] && map[i][j] == '0') {
					int size = bfs(i, j, groupId);
					list.add(size);
					groupId++;
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == '1') {
					int sum = 1;
					Set<Integer> adjGroups = new HashSet<>();
					
					for(int d=0; d<4; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];
						
						if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
						if(group[nr][nc] > 0 && adjGroups.add(group[nr][nc])) {
							sum += list.get(group[nr][nc]);
						}
					}
					sb.append(sum % 10);
				} else {
					sb.append(0);
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		

		
	}
	
	static int bfs(int r, int c, int id) {
		Queue<Point> q = new LinkedList<>();
		
		q.offer(new Point(r, c));
		visited[r][c] = true;
		group[r][c] = id;
		int count = 1;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for(int d=0; d<4; d++) {
				int nr = cur.x + dr[d];
				int nc = cur.y + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				if(!visited[nr][nc] && map[nr][nc] == '0') {
					visited[nr][nc] = true;
					group[nr][nc] = id;
					count++;
					q.offer(new Point(nr,nc));
				}
			}
		}
		
		return count;
	}
		

}
