import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int getDistance(Node n) {
            return Math.abs(this.x - n.x) + Math.abs(this.y - n.y);
        }
    }

    static int N, length;
    static Node[] map;
    static Node house, work;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());
            map = new Node[N];
            length = Integer.MAX_VALUE;
            visited = new boolean[N];
            st = new StringTokenizer(br.readLine());
            work = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            house = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            for (int i = 0; i < N; i++) {
                map[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            route(0, work, 0);

            sb.append("#").append(tc).append(" ").append(length).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void route(int depth, Node myLoc, int distance) {
        if (distance > length) return;
        if (depth == N) {
            // 집으로 가는 것까지 계산
            length = Math.min(length, distance + house.getDistance(myLoc));
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                route(depth + 1, map[i], distance + myLoc.getDistance(map[i]));
                visited[i] = false;
            }
        }
    }
}
