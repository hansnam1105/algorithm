import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sound = new StringBuilder(br.readLine());
		int answer = 0;

		if (sound.length() % 5 != 0 || sound.charAt(0) != 'q') {
            bw.write("-1");
            bw.close();
            return;
        }
		
		int q = 0, u = 0, a = 0, c = 0, k = 0;
		
		for(int i=0; i<sound.length(); i++) {
			if(sound.charAt(i) == 'q') {
				q++;
				answer = Math.max(answer, q);
			}
			if(sound.charAt(i) == 'u') {
				u++;
			}
			if(sound.charAt(i) == 'a') {
				a++;
			}
			if(sound.charAt(i) == 'c') {
				c++;
			}
			if(sound.charAt(i) == 'k') {
				k++;
			}
            if (q < u || u < a || a < c || c < k) {
                bw.write("-1");
                bw.close();
                return;
            }
			/*
			 * q 3 u 3 a 3 c 1 k 1
			 * q 2 u 2 a 2 c 0 k 0
			 * 
			 */
			if(q >= u && u >= a && a >= c && c >= k && k>=1) {
				q--;
				u--;
				a--;
				c--;
				k--;
			}
			
		}
		if (q == 0 && u == 0 && a == 0 && c == 0 && k == 0) {
            bw.write(String.valueOf(answer));
        } else {
            bw.write("-1");
        }
		bw.flush();
		bw.close();
		

	}

}
