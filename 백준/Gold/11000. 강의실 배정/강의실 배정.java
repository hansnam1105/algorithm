import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node>{
        int from, to;

        public Node(int from, int to){
            this.from = from;
            this.to = to;
        }

        @Override
        public int compareTo(Node o){
            if(this.from != o.from) {
            	return this.from - o.from;
            }
            return this.to - o.to;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Node[] lectures = new Node[N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            lectures[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(lectures);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(lectures[0].to);
        
        for(int i=1; i<lectures.length; i++) {
            if(lectures[i].from >= pq.peek()){
                pq.poll();
            }
            pq.offer(lectures[i].to);
        }

        bw.write(String.valueOf(pq.size()));
        bw.flush();
        bw.close();
    }
}
