import java.util.*;
import java.io.*;

public class Main {

    static int N, M, K;
    static long MOD = 1000000007;
    static long[] arr;
    static long[] tree; // 세그먼트 트리

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        tree = new long[4*N];

        build(1, 1, N);
        StringBuilder sb = new StringBuilder();

        for(int i=0; i < M+K; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Integer.parseInt(st.nextToken());

            if(a == 1){
                update(1,1, N, b, c);

            } else if (a == 2) {
                long result = query(1, 1, N, b, (int) c);
                sb.append(result).append("\n");
            }
        }

        System.out.println(sb);

    }

    static long build(int node, int start, int end){
        if(start == end){
            return tree[node] = arr[start] % MOD;
        }
        int mid = (start + end) / 2;
        long left = build(node * 2, start, mid);
        long right = build(node * 2 + 1, mid+1, end);

        return tree[node] = (left * right) % MOD;
    }

    static long query(int node, int start, int end, int left, int right) {
        // 범위 밖인 경우
        if (right < start || end < left) {
            return 1; // 곱셈에 항등원은 1이므로
        }

        // 현재 구간이 [left, right]에 완전히 포함될 경우
        if (left <= start && end <= right) {
            return tree[node];
        }

        // 일부만 겹치는 경우
        int mid = (start + end) / 2;
        long leftVal = query(node*2, start, mid, left, right);
        long rightVal = query(node*2+1, mid+1, end, left, right);

        return (leftVal * rightVal) % MOD;
    }

    // 세그먼트 트리 업데이트 함수
    // node: 현재 노드 번호
    // start, end: 현재 노드가 관리하는 구간
    // idx: 업데이트할 인덱스
    // val: 업데이트할 값
    static long update(int node, int start, int end, int idx, long val) {
        if (idx < start || idx > end) {
            // 업데이트할 인덱스가 이 노드 구간 밖에 있으면 현재 노드 값 변동 없음
            return tree[node];
        }

        // 리프 노드
        if (start == end) {
            return tree[node] = val % MOD;
        }

        int mid = (start + end) / 2;
        long leftVal = update(node*2, start, mid, idx, val);
        long rightVal = update(node*2+1, mid+1, end, idx, val);

        return tree[node] = (leftVal * rightVal) % MOD;
    }
}
