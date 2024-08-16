import java.io.*;

public class Main {
		
	static int[] answer = new int[7];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] dwarf = new int[9];
		
		for(int i=0; i<9; i++) {
			dwarf[i] = Integer.parseInt(br.readLine());
		}
		
		findDwarfs(0, 0, 0, dwarf);
		System.out.println(sb);
		
	}
	
	public static void findDwarfs(int cnt, int start, int total, int[] dwarf) {
		if(cnt == 7) {
			if(total == 100) {
				for(int i=0; i<7; i++) {
					sb.append(answer[i]).append("\n");
				}
			}
			return;
		}
		
		for (int i = start; i < 9; i++) {
			answer[cnt] = dwarf[i];  // 현재 난쟁이를 선택
			findDwarfs(cnt + 1, i + 1, total + dwarf[i], dwarf);  // 다음 난쟁이 선택
		}
	}

}
