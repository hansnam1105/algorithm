import java.io.*;

public class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        
        for(int i=0; i<n; i++){
           for(int j = 0; j < i; j++) {	// 공백
		        sb.append(" ");
	        }
    
	        for(int k = 0; k < (2 * n - 1) - (2 * i); k++){	// 별
		        sb.append("*");
	        }
	        sb.append('\n');
        }
        
        for(int i=0; i<n-1; i++){
            for(int j = 1; j < n-1-i; j++) {	// 공백
		        sb.append(" ");
	        }
            for(int k = 0; k < 3 + (2 * i); k++) {	// 별
		        sb.append("*");
            }
            sb.append('\n');
        }
        
        System.out.println(sb);
    }
}