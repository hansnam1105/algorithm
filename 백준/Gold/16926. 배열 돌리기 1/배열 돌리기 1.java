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

        // 결과 출력
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

        int layers = Math.min(N, M) / 2;

        for (int layer = 0; layer < layers; layer++) {
            int rStart = layer, rEnd = N - 1 - layer;
            int cStart = layer, cEnd = M - 1 - layer;

            // 위쪽 행
            for (int i = cStart; i < cEnd; i++) {
                newMap[rStart][i] = map[rStart][i + 1];
            }
            // 오른쪽 열
            for (int i = rStart; i < rEnd; i++) {
                newMap[i][cEnd] = map[i + 1][cEnd];
            }
            // 아래쪽 행
            for (int i = cEnd; i > cStart; i--) {
                newMap[rEnd][i] = map[rEnd][i - 1];
            }
            // 왼쪽 열
            for (int i = rEnd; i > rStart; i--) {
                newMap[i][cStart] = map[i - 1][cStart];
            }
        }

        // 안쪽 원소들 복사
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (newMap[i][j] == 0) {
                    newMap[i][j] = map[i][j];
                }
            }
        }

        return newMap;
    }
}
