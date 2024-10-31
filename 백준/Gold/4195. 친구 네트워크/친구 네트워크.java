import java.io.*;
import java.util.*;

public class Main{
    static final int MAX = 100001;
    static int F;
    static int[] parent;
    static int[] network;
    static HashMap<String, Integer> nameMap = new HashMap<>();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        for(int tc=0; tc<T; tc++){
            nameMap.clear();
            int idx = 0;
            int F = Integer.parseInt(br.readLine());
            parent = new int[2 * F];
            network = new int[2 * F];
            
            for(int i=0; i<2*F; i++){
                parent[i] = i;
                network[i] = 1;
            }
            
            for(int i=0; i<F; i++){
                st = new StringTokenizer(br.readLine());
                String friend1 = st.nextToken();
                String friend2 = st.nextToken();
                if(!nameMap.containsKey(friend1)){
                    nameMap.put(friend1, idx++);
                }
                if(!nameMap.containsKey(friend2)){
                    nameMap.put(friend2, idx++);
                }
                
                int idx1 = nameMap.get(friend1);
                int idx2 = nameMap.get(friend2);
                
                int parent1 = find(idx1);
                int parent2 = find(idx2);
                if(parent1 == parent2){
                    bw.write(network[parent1] + "\n");
                } else{
                    int newParent = union(idx1, idx2);
                    bw.write(network[newParent] + "\n");
                }
                
            }
            
            
        }      
        bw.flush();
        bw.close();

    }
    
    static int find(int a){
        if(parent[a] == a){
            return a;
        }
        return parent[a] = find(parent[a]);
    }
    
    static int union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        
        if(rootA != rootB){
            if(rootA < rootB){
                network[rootA] += network[rootB];
                parent[rootB] = rootA;
                return rootA;
            } else{
                network[rootB] += network[rootA];
                parent[rootA] = rootB;
                return rootB;
            }
        }
        
        return rootA;
    }
    
    
}