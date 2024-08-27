import java.util.*;
import java.io.*;

public class Solution {
    
    static int V, E;
    static int[] inDegree;
    static ArrayList<List<Integer>> graph;
    static Queue<Integer> queue;
    static ArrayList<Integer> answer;
    

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        for(int tc=1; tc<=10; tc++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            
            inDegree = new int[V+1];
            graph = new ArrayList<>();
            queue = new LinkedList<>();
            
            for(int i=0; i<=V; i++) {  // 0부터 V까지 초기화
                graph.add(new ArrayList<>());
            }
            
            st = new StringTokenizer(br.readLine());
            
            for(int i=0; i<E; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                
                graph.get(from).add(to);
                
                inDegree[to]++;
            }
            
            answer = new ArrayList<>();
            topologicalSort();
            
            // answer 배열을 공백으로 구분된 숫자로 나열
            StringBuilder sb = new StringBuilder();
            for (int num : answer) {
                sb.append(num).append(" ");
            }
            
            bw.write("#" + tc + " " + sb.toString().trim() + "\n"); // .trim()을 사용하여 마지막 공백 제거
        }
        bw.flush();
        bw.close();
        br.close();
    }
    
    public static void topologicalSort() {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= V; i++) {  // 1부터 V까지 확인
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();
            answer.add(current);

            for (int neighbor : graph.get(current)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
    }
}
