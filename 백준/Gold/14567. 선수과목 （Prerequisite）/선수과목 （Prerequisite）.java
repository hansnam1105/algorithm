/**
 * 한 학기에 들을 수 있는 과목 수에는 제한이 없다.
 * 모든 과목은 매 학기 항상 개설된다.
 * 
 */

import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[] course, semester;
	static ArrayList<List<Integer>> graph;
    static Queue<Integer> queue;
    static ArrayList<Integer> answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		course = new int[N+1];
		graph = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			graph.get(from).add(to);
			course[to]++;
		}
		
		answer = new ArrayList<>();
		semester = new int[N+1];
		topologicalSort();
		for(int i=1; i<semester.length; i++) {
			bw.write(String.valueOf(semester[i]) + " ");
		}
		bw.flush();
		bw.close();
		
	}
	
	static void topologicalSort() {
		queue = new LinkedList<>();
		for(int i=1; i<=N; i++) {
			if(course[i] == 0) {
				semester[i] = 1;
				queue.offer(i);
			}
			
		}
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			answer.add(current);
			
			for(int neighbor : graph.get(current)) {
				course[neighbor]--;
				if(course[neighbor] == 0) {
					queue.offer(neighbor);
					semester[neighbor] = semester[current] + 1;
				}
			}
		}
	}

}
