import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int count = 0;
		int[] bag = {5,3};
		while(N>0) {
			if(N>4) {
				if(N%5 == 0) {
					N-=bag[0];
					count++;
				} else if(N%3 == 0) {
					N-=bag[1];
					count++;
				} else {
					N-=bag[0];
					count++;
				}
			} else {
				N-=bag[1];
				count++;
			}
		}
		if(N == 0) {
			System.out.println(count);
		} else {
			System.out.println(-1);
		}
	}

}
