import java.util.*;
import java.io.*;

public class Solution {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            boolean[] visited = new boolean[10]; // 0부터 9까지의 숫자가 등장했는지 확인하는 배열
            int count = 0;
            int answer = 0;

            while (count < 10) {
                answer += N;
                char[] ch = String.valueOf(answer).toCharArray();
                for (char c : ch) {
                    int num = c - '0'; // 문자를 숫자로 변환
                    if (!visited[num]) {
                        visited[num] = true;
                        count++;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
