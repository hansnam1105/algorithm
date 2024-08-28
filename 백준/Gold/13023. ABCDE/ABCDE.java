import java.io.*;
import java.util.*;

public class Main {

    static List<List<Integer>> graph;
    static boolean[] visited;
    static boolean found;   
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        visited = new boolean[N];
        found = false;

        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        for (int i = 0; i < N; i++) {
            if (!found) {
                dfs(i, 0);
            }
        }
        int result = found ? 1 : 0;
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }
    
    public static void dfs(int current, int depth) {
        if (depth == 4) {
            found = true;
            return;
        }

        visited[current] = true;

        for (int neighbor : graph.get(current)) {
            if (!visited[neighbor]) {
                dfs(neighbor, depth + 1);
                if (found)
                    return;
            }
        }
        
        visited[current] = false;
    }
}
