import java.util.*;
import java.io.*;

public class Solution {
	
	static int N, start, maxVal;
	static ArrayList<List<Integer>> graph;
	static boolean[] visited;
	static int[] depth;
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int tc=1; tc<=10; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList<>();
			visited = new boolean[101];
			depth = new int[101];
			// make
			for(int i=0; i<101; i++) {
				graph.add(new ArrayList<>());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N/2; i++) {
				int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                
                graph.get(from).add(to);
			}
			
			maxVal = 0;
			int result = 0;
			bfs();
			for(int i=0; i<101; i++) {
				if(maxVal<=depth[i]) {
					maxVal = depth[i];
					if(result < i) result = i;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
	
	public static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int neighbor : graph.get(current)) {
                if(!visited[neighbor]) {
                	visited[neighbor] = true;
                	queue.offer(neighbor);
                	depth[neighbor] = depth[current]+1;
                }
            }
            
        }
    }


}
