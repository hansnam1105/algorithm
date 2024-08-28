import java.io.*;
import java.util.*;

public class Main {

	static int[] students;
	static ArrayList<List<Integer>> graph;
    static Queue<Integer> queue;
    static ArrayList<Integer> answer;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		students = new int[N+1];
		graph = new ArrayList<>();
		
		
		 for(int i=0; i<=N; i++) {  // 0부터 V까지 초기화
             graph.add(new ArrayList<>());
         }
         
         
         for(int i=0; i<M; i++) {
             st = new StringTokenizer(br.readLine());

             int from = Integer.parseInt(st.nextToken());
             int to = Integer.parseInt(st.nextToken());
             
             graph.get(from).add(to);
             
             students[to]++;
         }
         answer = new ArrayList<>();
         topologicalSort(N);
         StringBuilder sb = new StringBuilder();
         for (int num : answer) {
             sb.append(num).append(" ");
         }
         System.out.println(sb);
		
	}
	
	public static void topologicalSort(int N) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {  // 1부터 V까지 확인
            if (students[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();
            answer.add(current);

            for (int neighbor : graph.get(current)) {
                students[neighbor]--;
                if (students[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
    }

}
