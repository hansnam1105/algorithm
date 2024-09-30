/*
* 정점
* 간선
* 출발점 도착점
*/

import java.io.*;
import java.util.*;

public class Main {
    
    static class Edge implements Comparable<Edge>{
        int to, weight;
        
        public Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
        
        public int compareTo(Edge e){
            return this.weight - e.weight; // 비용 오름차순
        }                
    }
    
    static int N, M;
    static ArrayList<Edge>[] graph;
    static int[] dist;
    static int[] prev; // 경로 추적을 위한 배열
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        graph = new ArrayList[N+1];
        for(int i=0; i<=N; i++){
            graph[i] = new ArrayList<>();
        }
        dist = new int[N + 1];
        prev = new int[N + 1]; // 경로 추적을 위한 배열
        Arrays.fill(dist, (int) 1e9); // 무한대로 초기화
        Arrays.fill(prev, -1); // 초기값을 -1로 설정
        
        
        for(int i=1; i<=M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[start].add(new Edge(to, weight));
        }
        
        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        dist[S] = 0;
        
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(S, 0));
        while(!pq.isEmpty()){
            Edge cur = pq.poll();
            int curTo = cur.to;
            int curWeight = cur.weight;
            
            if(curWeight > dist[curTo]){
                continue;
            }
            
            for (int j = 0; j < graph[curTo].size(); j++) {
                int targetTo = graph[curTo].get(j).to;
                int targetWeight = graph[curTo].get(j).weight;

                int newWeight = dist[curTo] + targetWeight;
                if (dist[targetTo] > newWeight) {
                    dist[targetTo] = newWeight;
                    prev[targetTo] = curTo; // 이전 정점을 기록
                    pq.add(new Edge(targetTo, newWeight));
                }
            }
        }
        // 출발 도시에서 도착 도시까지 가는데 드는 최소 비용
        bw.write(dist[E] + "\n");
        
        Stack<Integer> path = new Stack();
        for(int i=E; i != -1; i=prev[i]){
            path.push(i);
        }
        
        bw.write(path.size() + "\n");
        while(!path.isEmpty()){
            bw.write(path.pop() + " ");
        }
        
        bw.flush();
        bw.close();
        br.close();

    }
}