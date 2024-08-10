import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        long N = Long.parseLong(st.nextToken());
        long M = Long.parseLong(st.nextToken());
        long result = 1;
        if( M <= N) {
            result = 0;
        }
        else {
            for (long i = N; i > 0; i--) {
                result *= i;
                result %= M;

                if (result == 0) {
                    break;
                }
            }
        }

        sb.append(result);
        System.out.println(sb);


    }
}
