import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int[] price = new int[4];
	static int[] calendar = new int[12];
	static int minVal;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        
        for(int tc = 1; tc <= T; tc++) {
        	st = new StringTokenizer(br.readLine());
        	for(int i=0; i<4; i++) {
        		price[i] = Integer.parseInt(st.nextToken());
        	}
        	st = new StringTokenizer(br.readLine());
        	for(int i=0; i<12; i++) {
        		calendar[i] = Integer.parseInt(st.nextToken());
        	}
        	minVal = Integer.MAX_VALUE;
        	dfs(0, 0);
        	
        	sb.append("#").append(tc).append(" ").append(minVal).append("\n");
        }
        
        System.out.println(sb);

	}
	
	public static void dfs(int month, int sum) {
		if(month>=12) {
			minVal = Math.min(minVal, sum);
			return;
		}
		
		if(calendar[month]==0) {
			dfs(month+1, sum);
		}
		else {
			dfs(month+1, sum+(calendar[month]*price[0])); //하루 이용권 사용한 경우
			dfs(month+1, sum+price[1]); // 한달 이용권 사용한 경우
	        dfs(month+3, sum+price[2]); // 세달 이용권 사용한 경우
            dfs(month+12, sum+price[3]); // 일년 이용권 사용한 경우
		}
		
		
	}

}
