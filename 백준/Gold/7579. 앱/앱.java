import java.io.*;
import java.util.*;

public class Main {
    
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[] memoryArr = new int[N];
        int[] costArr = new int[N];
        // 최대 비용
        int sumCost = 0;
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++){
            memoryArr[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            costArr[i] = Integer.parseInt(st.nextToken());
            sumCost += costArr[i];
        }
        
        int[][] dp = new int[N][sumCost+1];
        int result = Integer.MAX_VALUE;
        
        for(int i=0; i<N; i++){
            int memory = memoryArr[i];
            int cost = costArr[i];
            
            for(int j=0; j<=sumCost; j++){
                if(i==0){
                    if(j>=cost){
                        dp[i][j] = memory;
                    }
                }
                else{
                    if(j>=cost){
                        dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-cost] + memory);
                    }
                    else{
                        dp[i][j] = dp[i-1][j];
                    }
                }
                if(dp[i][j] >= M ){
                    result = Math.min(result, j);
                }
            } 
        }
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();

    }
}