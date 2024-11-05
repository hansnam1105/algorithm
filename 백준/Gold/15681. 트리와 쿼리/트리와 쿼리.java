import java.io.*;
import java.util.*;

public class Main{
    
    static int N, R, Q;
    static ArrayList<Integer>[] tree, list;
    static int[] arrQ, parent, size;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        
        list = new ArrayList[N+1];
        tree = new ArrayList[N+1];
        parent = new int[N+1];
        size = new int[N+1];
        arrQ = new int[Q];
        
        for(int i=1; i<=N; i++){
            list[i] = new ArrayList<>();
            tree[i] = new ArrayList<>();
        }
        
        
        for(int i=1; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list[x].add(y);
            list[y].add(x);
        }
        
        makeTree(R, -1);
        countSubTreeNodes(R);
        
        for(int i=0; i<Q; i++){
            int query = Integer.parseInt(br.readLine());
            sb.append(size[query]).append("\n");
        }
        
        System.out.println(sb);
    }
    
    static void makeTree(int curNode, int p){
        for(int node : list[curNode]){
            if(node != p){
                tree[curNode].add(node);
                parent[node] = curNode;
                makeTree(node, curNode);
            }
        }
    }
    
    static void countSubTreeNodes(int curNode){
        size[curNode] = 1;
        for(int node : tree[curNode]){
            countSubTreeNodes(node);
            size[curNode] += size[node];
        }
    }
}