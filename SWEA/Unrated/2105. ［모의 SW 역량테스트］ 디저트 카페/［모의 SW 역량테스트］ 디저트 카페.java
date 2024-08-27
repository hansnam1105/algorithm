/**
 * 디저트 카페
 */

import java.io.*;
import java.util.*;

public class Solution {

	static int N, maxSize, startR, startC;

	// 탐색 순서: 우하, 좌하, 좌상, 우상
	static int[] dr = { 1, 1, -1, -1 };
	static int[] dc = { 1, -1, -1, 1 };

	static int[][] map;
	static boolean[][] visited;
	static boolean[] dessert;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			maxSize = -1;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					visited = new boolean[N][N]; // 방문 여부 확인
					dessert = new boolean[101]; // 방문 카페 디저트 수 등록
					// 시작 좌표
					startR = i;
					startC = j;
					dfs(0, i, j, 0, 0);
				}
			}

			sb.append("#").append(tc).append(" ").append(maxSize).append("\n");
		}
		System.out.println(sb);

	}

	public static void dfs(int depth, int r, int c, int dir, int turnCount) {
		// depth - 카페 방문 횟수 (return 값)
		// turnCount - 사각형 -> 3번 방향을 꺾어서 도착
		if (depth > 0 && r == startR && c == startC && turnCount >= 3) {
			maxSize = Math.max(maxSize, depth);
			return;
		}
		

		// 한번 꺽은 방향은 다시 못 가도록 dir 활용
		for (int i = dir; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			// 카페 투어 중에 같은 숫자의 디저트를 팔고 있는 카페가 있으면 안 된다
			if (check(nr, nc) && !dessert[map[nr][nc]]) {
				//
				visited[nr][nc] = true;
				dessert[map[nr][nc]] = true;
				dfs(depth + 1, nr, nc, i, turnCount + (dir != i ? 1 : 0));
				// 백트레킹 다시 다른 경로를 탐색하기 위해
				dessert[map[nr][nc]] = false;
				visited[nr][nc] = false; // 이전 경로에서 방문했던 좌표는 다시 탐색할 수 있어야 한다
			}
			
			// 사각형을 만들기 위해 두 방향까지만 시작하도록 제한
            if (i == 1 && dir == 0) {
                break;
            }

		}
	}

	// map 안을 탐색하는지 & 새로운 곳을 탐색하는지
	public static boolean check(int r, int c) {
		if (r >= 0 && c >= 0 && r < N && c < N && !visited[r][c]) {
			return true;
		}
		return false;
	}

}
