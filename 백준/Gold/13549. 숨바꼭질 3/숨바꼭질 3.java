
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, K, result;
	static int map[] = new int[100001];
	static boolean[] visited = new boolean[100001];
	static int[] dx = { -1, 1, 2 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		result = Integer.MAX_VALUE;
		bfs();

		System.out.println(result);

	}

	static void bfs() {

		// 이동 위치와 시간을 담을 int배열 형태의 큐 선언
		Queue<int[]> q = new LinkedList<>();

		q.add(new int[] { N, 0 });
		visited[N] = true;

		while (!q.isEmpty()) {

			int current[] = q.poll();
			int cx = current[0];
			int cTime = current[1];

			if (cx == K) {
				// 동생을 찾았다면
				result = cTime;
			}

//			for (int d = 0; d < 3; d++) {
//				if (d == 2) {
//					// 순간이동이라면
//					int nx = cx*dx[2];
//					//타임은 증가하지 않는다.
//					if(nx >= 0 && nx < 100001 && !visited[nx]) {
//						visited[nx] = true;
//						q.add(new int[] {nx,cTime});
//					}
//				}
//				else {
//					// 순간이동 아니라 그냥 이동이라면
//					int nx = cx + dx[d];
//					int nTime = cTime + 1;
//					if(nx >= 0 && nx < 100001 && !visited[nx]) {
//						visited[nx] = true;
//						q.add(new int[] {nx,nTime});
//					}
//				}
//			}

			// 순간이동이라면
			int nx = cx * 2;
			// 타임은 증가하지 않는다.
			if (nx >= 0 && nx < 100001 && !visited[nx]) {
				visited[nx] = true;
				q.add(new int[] { nx, cTime });
			}

			for (int d = 0; d < 2; d++) {
				// 순간이동 아니라 그냥 이동이라면
				nx = cx + dx[d];
				int nTime = cTime + 1;
				if (nx >= 0 && nx < 100001 && !visited[nx]) {
					visited[nx] = true;
					q.add(new int[] { nx, nTime });
				}
			}

		}
	}

}
