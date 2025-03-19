import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());

        int d = 1;


        while ((1 << d) < K) {
            K-= (1 << d);
            d++;
        }

        int index = K - 1;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < d; i++){
            if(((index >> (d - 1 - i)) & 1) == 1) {
                sb.append("7");
            } else {
                sb.append("4");
            }
        }
        System.out.println(sb.toString());


    }
}
