/**
 * 16926 배열 돌리기
 * 배열을 반시계로 돌리면서
 * 4변 (위쪽, 오른쪽, 아래쪽, 왼쪽)의 회전시 값 변화를 계산했다
 * 안쪽 사각형은 N,M 중 작은 값 /2 만큼 있다
 * 1. R 만큼 회전 횟수를 반복한다
 * 2. 배열에 존재하는 사각형을 layer로 계산
 * 3. 사각형 마다 위쪽, 오른쪽, 아래쪽, 왼쪽 계산
 * 
 */

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = parseInt(st.nextToken());
        int M = parseInt(st.nextToken());
        int R = parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = parseInt(st.nextToken());
            }
        }

        // 회전 횟수에 대해 반복
        for (int r = 0; r < R; r++) {
            map = rotateMap(map, N, M);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    private static int[][] rotateMap(int[][] map, int N, int M) {
        int[][] newMap = new int[N][M];

        int layers = Math.min(N, M) / 2; //배열의 총 사각형 개수

        for (int layer = 0; layer < layers; layer++) {
            int rStart = layer;
            int rEnd = N - 1 - layer;
            int cStart = layer;
            int cEnd = M - 1 - layer;

            // 위쪽 행
            for (int i = cStart; i < cEnd; i++) {
                newMap[rStart][i] = map[rStart][i + 1];  // 기존의 오른쪽 +1 값
            }
            // 오른쪽 열
            for (int i = rStart; i < rEnd; i++) {
                newMap[i][cEnd] = map[i + 1][cEnd];  // 기존의 위쪽 +1 값
            }
            // 아래쪽 행
            for (int i = cEnd; i > cStart; i--) {
                newMap[rEnd][i] = map[rEnd][i - 1]; // 기존의 왼쪽 +1 값
            }
            // 왼쪽 열
            for (int i = rEnd; i > rStart; i--) {
                newMap[i][cStart] = map[i - 1][cStart]; // 기존의 아래쪽 값
            }
        }

        return newMap;
    }
}
