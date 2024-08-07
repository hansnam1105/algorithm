import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

		for(int test_case = 1; test_case <= T; test_case++)
		{
		
			int N;
			N = sc.nextInt();
			long[] answer = new long[N]; //결과값 저장 배열
			
			long[] pi = new long[N*2]; //정상가 할인가 섞이는 배열
			boolean[] used = new boolean[N*2]; //완전 탐색중 검증해야할 값은 true 스킵값 false
			
			for(int i=0; i<N*2; i++) {
				pi[i] = sc.nextLong();
				used[i] = true;
			}
			//검증
			int ans_idx = 0;
			for(int i=0; i<N*2; i++) {
				if(used[i]) {
					long salePrice = pi[i]; //할인가 저장
					for(int j=i; j<N*2; j++) { //원가 있는지 탐색
						long originalPrice = salePrice * 4 / 3;
						if(pi[j] == originalPrice && used[j]) {
							answer[ans_idx] = salePrice;
							used[j] = false;
							used[i] = false;
							ans_idx++;
							break;
						}
					}

				}
			}
			System.out.print("#" + test_case + " ");
			for(int i=0; i<N; i++) {
				System.out.print(answer[i] + " ");
			}
            System.out.println();
		}
	}
}