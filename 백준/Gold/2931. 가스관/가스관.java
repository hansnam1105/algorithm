import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[][] map = new char[R][C];
        boolean[] visited = new boolean[4];
        int[] dr = { 0, -1, 0, 1 };
        int[] dc = { -1, 0, 1, 0 };

		for(int i = 0; i < R; i++) {
			char[] charArr = br.readLine().toCharArray();
			for(int j = 0; j < C; j++) {
				if(charArr[j] == 'M'||charArr[j] == 'Z') {
					map[i][j] = '*';
				}
				else map[i][j] = charArr[j];				
			}
		}
		
		//좌 상 우 하 기준 0~4, 설치 못하는 가스관 추가
		HashSet<Character>[] setArr = new HashSet[4]; 
		for(int i = 0; i < 4; i++) {
			HashSet<Character> set = new HashSet<>();
			set.add('.');
			set.add('*');
			if(i%2==0) set.add('|');
			else set.add('-');
            /*
             * 좌측(0): '3', '4' X
             * 상측(1): '2', '3' X
             * 우측(2): '1', '2' X
             * 하측(3): '1', '4' X
             */
			if(i == 0) {
				set.add('3');
				set.add('4');
			}else if(i == 1) {
				set.add('2');
				set.add('3');
			}else if(i == 2) {
				set.add('1');
				set.add('2');
			}else if(i == 3) {
				set.add('1');
				set.add('4');
			}
			// 방향 별 HashSet 삽입
			setArr[i] = set;			
		}
		
		
		loop:for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
                if (map[i][j] != '.')
                    continue;
                Arrays.fill(visited, false);
				int cnt = 0;
				for(int k = 0; k < 4; k++) {
                    int nr = i + dr[k];
                    int nc = j + dc[k];
					//set에 없는 값이라면 연결되야하는 통로!
                    if (nr >= 0 && nr < R && nc >= 0 && nc < C && !setArr[k].contains(map[nr][nc])) {
						visited[k] = true;
						cnt++;
					}
				}
				if(cnt == 0) continue;
				else if(cnt == 1 || cnt == 3) {
					for(int k = 0; k < 4; k++) {
						int nr = i+dr[k];
						int nc = j+dc[k];
						//set에 없는 값이라면 연결되야하는 통로!
                        if (nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc] == '*') {
							visited[k] = true;
							cnt++;
							break;
						}
					}
				}
				
				// 맞는 통로 찾기
				sb.append(i+1).append(" ").append(j+1).append(" ");
				if(cnt == 4) sb.append('+');
				else if(visited[1]&&visited[3]) sb.append('|');
				else if(visited[0]&&visited[2]) sb.append('-');
				else if(visited[2]&&visited[3]) sb.append('1');
				else if(visited[1]&&visited[2]) sb.append('2');
				else if(visited[0]&&visited[1]) sb.append('3');
				else if(visited[0]&&visited[3]) sb.append('4');
				sb.append("\n");
				break loop;
			}
		}
			
		System.out.println(sb);
	}
}