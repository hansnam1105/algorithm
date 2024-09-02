/**
 * 1. 막대를 더해 합이 X 보다 크다면
 * 1. 막대 중 길이가 가장 짧은 것을 절반으로 자른다
 * 2. 만약, 막대의 절반 중 하나를 버리고 남아있는 막대의 길이의 합이 X 보다 크거나 같다면
 * 위에서 자른 막대의 절반 중 하나를 버린다
 */

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int X = sc.nextInt();
		
		int result = 0;
		
		for(int i=0; i<7; i++) {
			if ((X & (1<<i))>0) result++;
		}
		
		System.out.println(result);
	}

}
