/**
 * N*N 배열에서 겹치지 않는 2개의 가로로 연속되는 벌통 사이즈 M
 * 
 * 조합으로 우선 벌통을 만든다
 * 만약 꿀의 양이 C 보다 클 경우 부분집합을 통해 최대 값을 구한다.
 * 부분집합 부분은 비트마스킹 연습
 * 
 */

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Solution {

    static int N, M, C, result;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = parseInt(st.nextToken());
            M = parseInt(st.nextToken());
            C = parseInt(st.nextToken());

            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = parseInt(st.nextToken());
                }
            }

            result = 0;
            combination(0, 0, 0, 0);

            sb.append("#").append(tc).append(" ").append(result).append("\n");

        }
        System.out.println(sb);
    }

    public static void combination(int worker, int sum, int row, int col) {
        if (worker == 2) {
            result = Math.max(result, sum);
            return;
        }

        r: for (int i = row; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (row == i && j < col)
                    continue;
                if (N - j < M) {
                    continue r;
                }
                int honeySum = 0;
                int profit = 0;
                int[] temp = new int[M];
                for (int k = 0; k < M; k++) {
                    honeySum += map[i][j + k];
                    profit += map[i][j + k] * map[i][j + k];
                    temp[k] = map[i][j + k];
                }
                if (honeySum <= C) {
                    combination(worker + 1, sum + profit, i, j + M);
                } else
                    combination(worker + 1, sum + powerset(temp), i, j + M);

            }
        }
    }

    public static int powerset(int[] temp) {
        int setMoney = 0;
        int n = temp.length;
        for (int i = 0; i < (1 << n); i++) { // i -> temp 길이 즉 M까지
            int sum = 0, profit = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) { // i & (1<<j) 0 아닐시
                    sum += temp[j];
                    profit += temp[j] * temp[j];
                }
            }
            if (sum <= C) {
                setMoney = Math.max(setMoney, profit);
            }
        }
        return setMoney;
    }

}
