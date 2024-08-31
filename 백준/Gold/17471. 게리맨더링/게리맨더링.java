
/**
 * 유니온 파인드로 완성 시키려다 실패
 * 그룹별 조회 하다 groupA 연결 확인 후 parents가 바뀌며 groupB 확인할때 영향
 * union find 말고 bfs 통해 두 그룹 연결성 확인
 * 
 */

import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] population;
    static ArrayList<Integer>[] adj;
    static int minDiff = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        population = new int[N + 1];
        adj = new ArrayList[N + 1];
        
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            for (int j = 0; j < num; j++) {
                int neighbor = Integer.parseInt(st.nextToken());
                adj[i].add(neighbor);
            }
        }

        // 비트 마스킹 시도
        for (int i = 1; i < (1 << N); i++) {
            checkGarry(i);
        }

        int result = (minDiff == Integer.MAX_VALUE ? -1 : minDiff);

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }

    static void checkGarry(int bitmask) {
        ArrayList<Integer> groupA = new ArrayList<>();
        ArrayList<Integer> groupB = new ArrayList<>();

        // 비트마스크로 그룹 분할
        for (int i = 0; i < N; i++) {
            if ((bitmask & (1 << i)) != 0) {
                groupA.add(i + 1);
            } else {
                groupB.add(i + 1);
            }
        }

        // 빈 그룹 체크
        if (groupA.size() == 0 || groupB.size() == 0)
            return;

        // 두 그룹이 모두 연결되어 있으면 인구 차이 계산
        if (isConnected(groupA) && isConnected(groupB)) {
            int sumA = 0, sumB = 0;
            for (int i : groupA) {
                sumA += population[i];
            }
            for (int i : groupB) {
                sumB += population[i];
            }
            minDiff = Math.min(minDiff, Math.abs(sumA - sumB));
        }
    }

    static boolean isConnected(ArrayList<Integer> group) {
    boolean[] visited = new boolean[N + 1];
    Queue<Integer> queue = new LinkedList<>();

    queue.offer(group.get(0));
    visited[group.get(0)] = true;

    int count = 1;
    while (!queue.isEmpty()) {
        int cur = queue.poll();
        for (int neighbor : adj[cur]) {
            // 이웃 노드가 현재 그룹에 속하고, 방문하지 않은 노드라면
            if (!visited[neighbor] && group.contains(neighbor)) {
                visited[neighbor] = true;
                queue.add(neighbor);
                count++;
            }
        }
    }

    // 방문한 노드의 수와 그룹의 크기가 같아야 연결된 것으로 간주
    return count == group.size();
}

}
