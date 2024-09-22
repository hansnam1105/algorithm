import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Edge>[] adj;

    /*
    * 간선 정보 저장
    */
    static class Edge implements Comparable<Edge> {
        int to;
        int cost;
        Edge(int to, int cost){
            this.to = to;
            this.cost = cost;
        }
        public int compareTo(Edge o){
            return this.cost - o.cost;
        }
    }
    
    public static void main(String[] args) throws Exception {
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    		StringTokenizer st;

    		st = new StringTokenizer(br.readLine());
    		N = Integer.parseInt(st.nextToken());
    		M = Integer.parseInt(st.nextToken());

    		adj = new ArrayList[N+1];
    		for(int i = 1; i <= N; i++){
    			adj[i] = new ArrayList<>();
    		}

            // 간선 정보 입력 인접 리스트 
    		for(int i = 0; i < M; i++){
    			st = new StringTokenizer(br.readLine());
    			int u = Integer.parseInt(st.nextToken());
    			int v = Integer.parseInt(st.nextToken());
    			int c = Integer.parseInt(st.nextToken());

    			adj[u].add(new Edge(v, c));
    			adj[v].add(new Edge(u, c));
    		}

    		int minCost = getMinCowCost(1, N);
    		bw.write(minCost + "\n");

    		bw.flush();
    		bw.close();
    }

    // 다익스트라 알고리즘을 사용하여 최소 비용 계산
    static int getMinCowCost(int start, int end){
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.offer(new Edge(start, 0));

        while(!pq.isEmpty()){
            Edge current = pq.poll();
            int curNode = current.to;
            int curCost = current.cost;

            if(dist[curNode] < curCost) continue;

            for(Edge next : adj[curNode]){
                int nextNode = next.to;
                int nextCost = curCost + next.cost;

                if(dist[nextNode] > nextCost){
                    dist[nextNode] = nextCost;
                    pq.offer(new Edge(nextNode, nextCost));
                }
            }
        }

        return dist[end];
    }
}
