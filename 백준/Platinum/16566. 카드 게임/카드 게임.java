
/**
 * P16566
 */
import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static int[] minsu;
    static int[] game;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        minsu = new int[M];
        game = new int[M + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            minsu[i] = Integer.parseInt(st.nextToken());
            game[i] = i;
        }
        game[M] = M;

        Arrays.sort(minsu);

        st = new StringTokenizer(br.readLine());
        // 철수 카드를 조작 가능
        // 카드 버리고 민수 몰래 다시 들고 오거나 민수한테 없는 카드 중 가능
        // 민수는 철수가 낼 카드보다 큰 카드 있다면 가장 작은것
        for (int i = 0; i < K; i++) {
            int cheol = Integer.parseInt(st.nextToken());
            int search = binarySearch(cheol, minsu);
            int index = find(search);
            sb.append(minsu[index] + "\n");
            union(index, index + 1);

        }

        System.out.println(sb);

    }

    static int binarySearch(int target, int[] arr) {

        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] <= target) {
                left = mid + 1; // 민수가 철수가 낼 카드보다 큰 카드 중 가장 작은 카드 내도록 설정
            } else {
                right = mid - 1;
            }
        }
        return left;

    }

    static int find(int a) {
        if (game[a] == a) {
            return a;
        }
        return game[a] = find(game[a]);
    }

    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) {
            return false;
        } else if (aRoot > bRoot) {
            game[b] = a;
        } else {
            game[a] = b;
        }

        return true;
    }
}