import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        Map<String, Integer> map = new HashMap<>();
        Map<Integer, String> map2 = new HashMap<>();
        
        for(int i=0; i<N; i++){
            String name = br.readLine();
            map.put(name, i+1);
            map2.put(i+1, name);
        }
        
        for(int i=0; i<M; i++){
            String cmd = br.readLine();
            
            if(Character.isDigit(cmd.charAt(0))) {
                bw.write(map2.get(Integer.parseInt(cmd)) + "\n");
            } else{
                bw.write(map.get(cmd) + "\n");
            }
        }
        
        bw.flush();
        bw.close();
    }
}