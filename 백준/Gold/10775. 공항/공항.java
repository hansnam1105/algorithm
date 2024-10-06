import java.io.*;

/**
 * 신승원 박승원 승원이 에브리웨어
 * G 개의 게이트 1~G
 * P 개의 비행기 수선대로 도착 i번째 비행기를 1번부터 g 번째 게이트 하나에 영구적 도킹
 * 어느 게이트에도 도킹 할 수 없다면 폐쇄
 * 
 * P->g 유니온 파인드 문제 같다
 * 유니온 파인드 문제 흐름
 * 1. parent 배열을 선언하고 각 게이트가 자신을 부모로 가리키도록 초기화
 * 2. 반복문을 통해 각 비행기의 도킹 가능 게이트 처리
 * pGate에 대해 연결된 가장 높은 게이트를 findSet(pGate)로 차즌다
 * 3. 도킹이 가능한 게이트 있으면 union을 사용해 더 작은 게이트로 이동할 수 있도록 연산
 * 
 */

public class Main {
	
	static int G, P;
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());
		
		parent = new int[G+1];
		for(int i=1; i<=G; i++) {
			parent[i] = i;
		}
		
		int answer = 0;
		
		for(int i=0; i<P; i++) {
			int pGate = Integer.parseInt(br.readLine());
			
			int availableGate = findSet(pGate);
			
			if(availableGate == 0){
				break;
			}
			
			answer++;
			union(availableGate - 1, availableGate);
			
		}
		
		System.out.println(answer);
		
	}
	
	static int findSet(int gate) {
		if(gate == parent[gate]) {
			return gate;
		}
		
		return parent[gate] = findSet(parent[gate]);
	}
	
	static void union(int gateA, int gateB) {
		int aRoot = findSet(gateA);
		int bRoot = findSet(gateB);
		
		if(aRoot == bRoot) return;
		
		parent[bRoot] = aRoot;
	}
	
}
