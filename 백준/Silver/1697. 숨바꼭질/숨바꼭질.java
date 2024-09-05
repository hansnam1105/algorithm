import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{

    static int N,K;
    static int d[] = {-1,1,2};
    static boolean visited[];
    static int cnt;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited = new boolean[100001]; // 0부터 100000까지

        cnt = bfs(N);

        System.out.println(cnt);

    }

    public static int bfs(int n){

        Queue<int[]> queue = new LinkedList<>();

        visited[n] = true;

        queue.offer(new int[]{n,0});

        while(!queue.isEmpty()){

            int current[] = queue.poll();

            int move = current[0];
            int cnt = current[1];

            if(move == K){
                return cnt;
            }
            int next = 0;
            for(int i = 0; i < 3; i++){

                //int next = 0;
                if(i==2){
                    next = move*2;
                }else{

                    next = move + d[i];
                }
                if(next <= 100000 && next >= 0 && !visited[next]){

                    visited[next] = true;
                    queue.add(new int[]{next,cnt+1});
                }
            }
        }
        return 0;
    }
}