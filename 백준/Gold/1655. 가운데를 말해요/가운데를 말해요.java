import java.io.*;
import java.util.*;
import java.util.*;
/**
 * 가운데를 말해요
 * 힙 자료구조
 * 최대 힙과 최소 힙을 사용해 수열 중간값 유지
 * 최대 힙에 중간값 이하의 수들 저장
 * 최소 힙에 중간값 이상으 ㅣ수 저장
 * 
 */

public class Main {	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2-o1);
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(br.readLine());
			if(maxHeap.size() == minHeap.size())
				maxHeap.offer(num);
			else
				minHeap.offer(num);
			
			if(!minHeap.isEmpty() && !maxHeap.isEmpty()) {
				if(minHeap.peek() < maxHeap.peek()) {
					int tmp = minHeap.poll();
					minHeap.offer(maxHeap.poll());
					maxHeap.offer(tmp);
				}
			}
			bw.write(maxHeap.peek() + "\n");
		}
		bw.flush();
		bw.close();
		
	}
	
}
