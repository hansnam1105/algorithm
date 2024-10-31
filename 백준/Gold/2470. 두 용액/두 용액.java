import java.io.*;
import java.util.*;

public class Main{
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        
        int left = 0;
        int right = n-1;
        int ansL = 0, ansR = right;
        int min = Integer.MAX_VALUE;
        while(left < right){
            int sum = arr[left] + arr[right];
            if(Math.abs(sum) < min){
                ansL = left;
                ansR = right;
                min = Math.abs(sum);
            } else if(sum < 0){
                left++;
            } else{
                right--;
            }            
        }
        bw.write(arr[ansL] + " " + arr[ansR]);
        bw.flush();
        bw.close();
    }
}