import java.io.*;
import java.util.*;

public class Main{
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        for(int tc=0; tc<T; tc++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            int dist = y - x;
            int sqrtDist = (int)Math.sqrt(dist);
            
            if(dist == sqrtDist * sqrtDist){
                bw.write(2*sqrtDist-1 + "\n");
            }
            else if(dist <= sqrtDist * sqrtDist + sqrtDist){
                bw.write(2*sqrtDist + "\n");
            }
            else {
                bw.write(2*sqrtDist+1 + "\n");
            }
            
            
        }
        bw.flush();
        bw.close();
    }
    
}