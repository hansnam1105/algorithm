import java.io.*;

public class Main {
    
    static int N, k;
    
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        N = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        
        int low = 1;
        int high = k;
        
        while(low < high) {
            int mid = (low + high) / 2;
            int count = 0;
            
            for(int i=1; i<=N; i++){
                count += Math.min(mid/i, N);
            }
            
            if(k <= count){
            	high = mid;
            }
            else {
                low = mid + 1;
            }
            
        }
        
        bw.write(low + "\n");
        bw.flush();
        bw.close();
    }
}