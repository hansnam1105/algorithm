import java.io.*;
import java.util.*;

public class Main {

    static int n, cnt;
    static int[] link, visitOrder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        for (int test = 0; test < t; test++) {
            n = Integer.parseInt(br.readLine());
            link = new int[n + 1];
            visitOrder = new int[n + 1]; // 방문 순서를 기록

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                link[i] = Integer.parseInt(st.nextToken());
            }

            cnt = 0;
            Arrays.fill(visitOrder, 0); // 방문 순서 초기화

            for (int i = 1; i <= n; i++) {
                if (visitOrder[i] == 0) {
                    dfs(i, 1);
                }
            }

            sb.append(n - cnt).append("\n"); // 팀에 속하지 않은 학생 수
        }

        System.out.print(sb);
    }

    static void dfs(int pos, int order) {
        visitOrder[pos] = order; // 현재 방문 순서 저장
        int next = link[pos];

        if (visitOrder[next] == 0) {
            dfs(next, order + 1); // 다음 노드 방문
        } else if (visitOrder[next] > 0) {
            // 사이클 발견: 현재 노드가 방문 중인 경로에 포함됨
            cnt += (visitOrder[pos] - visitOrder[next] + 1);
        }

        visitOrder[pos] = -1; // 검색 완료 표시
    }
}