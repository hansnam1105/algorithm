/**
 * 키 순서 문제
 * 그래프 탐색으로 
 * 한 학생마다 가 수 있는 모든 정점을 탐색해서
 * 그래프에 들어가 있는 단방향 그래프 들의 정점은 학생보다 키가 큰 학생들
 * 역방향 그래프를 만들어 학생보다 키가 작은 학생들의 정점을 구한다
 * 두 Set 의 합을 구해 N-1과 같다면 자신의 키가 몇 번째인지 알 수 있다.
 */

import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int from, to;

        public Node(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    static int N, M;
    static ArrayList<Node>[] list, reverseList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());


        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList[N + 1];
        reverseList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
            reverseList[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(new Node(a, b));
            reverseList[b].add(new Node(b, a));
        }

        int count = 0;

        for (int i = 1; i <= N; i++) {
            Set<Integer> greater = new HashSet<>();
            dfs(i, 0, greater, list, new boolean[N + 1]);
            Set<Integer> smaller = new HashSet<>();
            dfs(i, 0, smaller, reverseList, new boolean[N + 1]);
            if (greater.size() + smaller.size() == N - 1)
                count++;
        }

        bw.write(String.valueOf(count));

        bw.flush();
        bw.close();

    }

    static void dfs(int node, int end, Set<Integer> set, ArrayList<Node>[] graph, boolean[] visited) {
        if (end != 0 && node == end) {
            return;
        }
        for (int i = 0; i < graph[node].size(); i++) {
            Node next = graph[node].get(i);
            if (visited[next.to])
                continue;
            visited[next.to] = true;
            set.add(next.to);
            dfs(next.to, end, set, graph, visited);
        }

        return;
    }

}
