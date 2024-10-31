import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int x = Integer.parseInt(br.readLine());
                
        int left = 0;
        int right = N - 1;
        int count = 0;
        Arrays.sort(arr);
        while(left < right){
            int sum = arr[left] + arr[right];
            
            if(sum == x){
                count++;
                left++;
                right--;
            }
            else if(sum < x){
                left ++;
            }
            else{
                right --;
            }
        }
        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }
}