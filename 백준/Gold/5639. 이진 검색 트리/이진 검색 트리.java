import java.io.*;
import java.util.*;

public class Main {

    static int[] tree = new int[10001];
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int idx = 0;
        String input = null;
        while ((input = br.readLine()) != null && !input.equals("")) {
            tree[idx++] = Integer.parseInt(input);
        }

        // 이진 탐색
        BinarySearch(0, idx - 1);
        bw.flush();
        bw.close();

    }

    static void BinarySearch(int start, int end) throws Exception {
        if (start > end) {
            return;
        }

        int root = tree[start];
        int mid = start + 1;

        // 왼쪽과 오른쪽 나누는 경계 찾기
        while (mid <= end && tree[mid] < root) {
            mid++;
        }

        // 왼쪽 서브트리 탐색
        BinarySearch(start + 1, mid - 1);
        // 오른쪽 서브트리 탐색
        BinarySearch(mid, end);
        bw.write(String.valueOf(root));
        bw.newLine();
    }

}