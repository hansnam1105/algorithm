import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            int check = 0;
            if (a >= 10) { check++; }
            if (b >= 10) { check++; }
            if (c >= 10) { check++; }

            String answer = "";
            switch (check) {
                case 3: answer = "triple-double"; break;
                case 2: answer = "double-double"; break;
                case 1: answer = "double"; break;
                default: answer = "zilch"; break;
            }

            System.out.printf("%d %d %d\n", a, b, c);
            System.out.println(answer);
            System.out.println();
        }
    }
}