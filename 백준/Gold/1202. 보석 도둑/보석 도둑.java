import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Jewel implements Comparable<Jewel> {
        int weight;
        int price;

        public Jewel(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }

        @Override
        public int compareTo(Jewel j) {
            return this.weight - j.weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        ArrayList<Jewel> jewels = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            jewels.add(new Jewel(weight, price));
        }

        ArrayList<Integer> bags = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            bags.add(Integer.parseInt(br.readLine()));
        }

        // 보석은 무게 기준으로 오름차순 정렬
        Collections.sort(jewels);
        // 가방은 용량 기준으로 오름차순 정렬
        Collections.sort(bags);

        long totalPrice = 0;
        int jewelIndex = 0;
        PriorityQueue<Integer> priceQueue = new PriorityQueue<>(Collections.reverseOrder());

        for (int bag : bags) {
            while (jewelIndex < jewels.size() && jewels.get(jewelIndex).weight <= bag) {
                priceQueue.add(jewels.get(jewelIndex).price);
                jewelIndex++;
            }

            if (!priceQueue.isEmpty()) {
                totalPrice += priceQueue.poll();
            }
        }

        bw.write(totalPrice + "\n");
        bw.flush();
        bw.close();

    }
}
