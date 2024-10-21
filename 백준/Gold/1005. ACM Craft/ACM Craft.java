import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        for(int tc=0; tc<T; tc++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            List<List<Integer>> adjList = new ArrayList<>();
            for(int i=0; i<N+1; i++){
                 adjList.add(new ArrayList<>());
            }
            int[] D = new int[N+1];
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<N+1; i++){
                D[i] = Integer.parseInt(st.nextToken());
            }
            
            int[] fromToCount = new int[N+1];
            
            for(int i=0; i<K; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                fromToCount[y]++;
                adjList.get(x).add(y);
            }
            
            int W = Integer.parseInt(br.readLine());
            
            int answer = topologicalSort(N, W, D, fromToCount, adjList);
            sb.append(answer).append("\n"); 
        }
        System.out.println(sb.toString());
    }
    
    static int topologicalSort(int N, int W, int[] D, int[] count, List<List<Integer>> adjList){
        Queue<Integer> queue = new LinkedList<>();
        int[] result = new int[N+1];
        
        for(int i=1; i<count.length; i++){
            result[i] = D[i];
            if(count[i] == 0){
                queue.add(i);
            }
        }
        
        while(!queue.isEmpty()){
            int current = queue.poll();
            
            for(int neighbor : adjList.get(current)){
                result[neighbor] = Math.max(result[current] + D[neighbor], result[neighbor]);
                count[neighbor]--;
                if(count[neighbor] == 0) queue.offer(neighbor);
            }
        }
        
        return result[W];
        
        
    }
    
    
}