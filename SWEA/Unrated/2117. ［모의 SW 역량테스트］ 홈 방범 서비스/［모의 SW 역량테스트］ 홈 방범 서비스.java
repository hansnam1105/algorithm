import java.io.*;
import java.util.*;

public class Solution {

    static int N, M, K, answer, maxProfit;
    static int[][] map;
    static ArrayList<int[]> list;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            list = new ArrayList<>();
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    int temp = Integer.parseInt(st.nextToken());
                    if(temp == 1) {
                        map[i][j] = 1;
                        list.add(new int[] {i, j});
                    }
                }
            }
            answer = 0;
            maxProfit = Integer.MIN_VALUE;
            solve();
            bw.write("#" + tc + " " + answer + "\n");
        }

        bw.flush();
        bw.close();
    }

    public static void solve() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                for(int k=1; k<=N+1; k++) {
                    int operationFee = k * k + (k - 1) * (k - 1);
                    int safeHouse = 0;
                    for(int[] house : list) {
                        int targetR = house[0];
                        int targetC = house[1];

                        if(getDistance(i, j, targetR, targetC) < k) {
                            safeHouse++;
                        }
                    }
                    int totalFee = safeHouse * M - operationFee;
                    if(totalFee >= 0) {
                    	answer = Math.max(answer , safeHouse);
                    }
                }
            }
        }
    }

    public static int getDistance(int r, int c, int targetR, int targetC) {
        return Math.abs(r - targetR) + Math.abs(c - targetC);
    }
}
