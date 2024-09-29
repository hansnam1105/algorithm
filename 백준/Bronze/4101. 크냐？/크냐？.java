import java.io.*;
import java.util.*;

public class Main{
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while(true){
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            if(num1 == 0 & num2 == 0){
                break;
            }
            if(num1 > num2){
                sb.append("Yes\n");
            } else{
                sb.append("No\n");
            }

        }
        System.out.println(sb);
    }
}