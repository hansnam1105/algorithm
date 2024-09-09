import java.io.*;
import java.util.*;

public class Main {

	
	static int N;
	static int[] parents;
	static List<Integer>[] list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        
        list = new ArrayList[N+1];
        for(int i=0; i<N+1; i++) {
        	list[i] = new ArrayList<>();
        }
        parents = new int[N+1];
        for (int i = 1; i < N+1; i++) {
            parents[i] = i; // 자신의 부모를 자신으로하여 대표자가 되도록 설정
        }
       
        for(int i=0; i<N-1; i++) {
        	st = new StringTokenizer(br.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	list[from].add(to);
        	list[to].add(from);
        }
        
        dfs(1, new boolean[N+1]);
        for(int i=2; i<parents.length; i++) {
        	bw.write(String.valueOf(parents[i]));
        	bw.newLine();
        }
        bw.flush();
        bw.close();
	}
	
	static void dfs(int cur, boolean[] visited) {
		visited[cur] = true;
		for(int i : list[cur]) {
			if(!visited[i]) {
				parents[i] = cur;
				dfs(i, visited);
			}
		}
	}

}
