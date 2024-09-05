import java.io.*;
import java.util.*;

public class Solution {

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
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());
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

            bw.write("#" + tc + " " + String.valueOf(count) + "\n");

        }
        
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
