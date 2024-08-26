import java.util.*;
import java.io.*;

public class Solution {

	static int N, mineCnt;
	static char[][] map;
	static boolean[][] visited;
	static int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				String temp = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = temp.charAt(j);
				}
			}
			int minClick = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (checkBomb(i, j) == 0 && map[i][j] != '*' && !visited[i][j]) {
						minClick += 1;
						bfs(i, j);
					}

				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '.') {
						if (!visited[i][j]) {
							visited[i][j] = true;
							map[i][j] = '0';
							minClick += 1;
						}
					}
				}
			}

			sb.append("#").append(tc).append(" ").append(minClick).append("\n");

		}
		System.out.println(sb);

	}

	public static void bfs(int r, int c) {
		ArrayDeque<Integer[]> deque = new ArrayDeque<>();
		visited[r][c] = true;
		deque.offer(new Integer[] { r, c });

		while (!deque.isEmpty()) {
			Integer[] cur = deque.poll();
			int bombCount = checkBomb(cur[0], cur[1]);
			map[cur[0]][cur[1]] = (char) (bombCount + '0');
			if (bombCount > 0) {
				continue;
			}
			for (int i = 0; i < 8; i++) {
				int nr = cur[0] + dy[i];
				int nc = cur[1] + dx[i];
				if (check(nr, nc)) {
					visited[nr][nc] = true;
					deque.offer(new Integer[] { nr, nc });

				}
			}
		}

	}

	public static int checkBomb(int r, int c) {
		int bomb = 0;
		for (int i = 0; i < 8; i++) {
			int nr = r + dy[i];
			int nc = c + dx[i];
			if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
				if (map[nr][nc] == '*') {
					bomb++;
				}
			}
		}
		return bomb;
	}

	public static boolean check(int r, int c) {
		if (r >= 0 && c >= 0 && r < N && c < N && map[r][c] != '*' && !visited[r][c]) {
			return true;
		}
		return false;
	}
}
