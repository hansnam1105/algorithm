/**
 * 상호의 배틀필드
 * 
문자	의미
.	평지(전차가 들어갈 수 있다.)
*	벽돌로 만들어진 벽
#	강철로 만들어진 벽
-	물(전차는 들어갈 수 없다.)
^	위쪽을 바라보는 전차(아래는 평지이다.)
v	아래쪽을 바라보는 전차(아래는 평지이다.)
<	왼쪽을 바라보는 전차(아래는 평지이다.)
>	오른쪽을 바라보는 전차(아래는 평지이다.)

 */

import java.util.*;
import java.io.*;

public class Solution {

	static int H, W, N;
	static char[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			int tankY = 0;
			int tankX = 0;
			map = new char[H][W];
			for (int i = 0; i < H; i++) {
				String temp = br.readLine();
				for (int j = 0; j < W; j++) {
					map[i][j] = temp.charAt(j);
					if (map[i][j] == '<' || map[i][j] == '>' || map[i][j] == '^' || map[i][j] == 'v') {
						tankY = i;
						tankX = j;
					}
				}
			}

			N = Integer.parseInt(br.readLine());
			char order[] = new char[N];
			order = br.readLine().toCharArray();

			for (int i = 0; i < N; i++) {
				if (order[i] == 'U') {
					map[tankY][tankX] = '^';// ^ 방향으로 머리를 돌리고
					if (tankY - 1 >= 0 && map[tankY - 1][tankX] == '.') {
						map[tankY][tankX] = '.';
						map[tankY - 1][tankX] = '^';
						tankY -= 1;
					}
				} else if (order[i] == 'D') {
					map[tankY][tankX] = 'v';// v 방향으로 머리를 돌리고
					if (tankY + 1 < H && map[tankY + 1][tankX] == '.') {
						map[tankY][tankX] = '.';
						map[tankY + 1][tankX] = 'v';
						tankY += 1;
					}
				} else if (order[i] == 'L') {
					map[tankY][tankX] = '<';// < 방향으로 머리를 돌리고
					if (tankX - 1 >= 0 && map[tankY][tankX - 1] == '.') {
						map[tankY][tankX] = '.';
						map[tankY][tankX - 1] = '<';
						tankX -= 1;
					}
				} else if (order[i] == 'R') {
					map[tankY][tankX] = '>';// > 방향으로 머리를 돌리고
					if (tankX + 1 < W && map[tankY][tankX + 1] == '.') {
						map[tankY][tankX] = '.';
						map[tankY][tankX + 1] = '>';
						tankX += 1;
					}
				} else if (order[i] == 'S') { // 발사!!!
					if (map[tankY][tankX] == '^') {
						for (int j = tankY - 1; j >= 0; j--) {// 벽에 닿을때까지
							if (map[j][tankX] == '*') {// 벽돌
								map[j][tankX] = '.';
								break;
							} else if (map[j][tankX] == '#') {// 강철
								break;
							}
						}
					} else if (map[tankY][tankX] == 'v') {
						for (int j = 1; j <= H - 1 - tankY; j++) {// 벽에 닿을때까지
							if (map[tankY + j][tankX] == '*') {// 벽돌
								map[tankY + j][tankX] = '.';
								break;
							} else if (map[tankY + j][tankX] == '#') {// 강철
								break;
							}
						}
					} else if (map[tankY][tankX] == '<') {
						for (int j = tankX - 1; j >= 0; j--) {// 벽에 닿을때까지
							if (map[tankY][j] == '*') {// 벽돌
								map[tankY][j] = '.';
								break;
							} else if (map[tankY][j] == '#') {// 강철
								break;
							}
						}
					} else if (map[tankY][tankX] == '>') {
						for (int j = 1; j <= W - 1 - tankX; j++) {// 벽에 닿을때까지
							if (map[tankY][tankX + j] == '*') {// 벽돌
								map[tankY][tankX + j] = '.';
								break;
							} else if (map[tankY][tankX + j] == '#') {// 강철
								break;
							}
						}
					}

				} // Else if Order 'S'

			} // End For i 0->N
			sb.append("#").append(tc).append(" ");
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
		} // End TestCase
		System.out.println(sb);

	}
}
