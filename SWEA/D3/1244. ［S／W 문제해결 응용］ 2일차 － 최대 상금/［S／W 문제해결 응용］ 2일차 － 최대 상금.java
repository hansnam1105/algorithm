
/**
 * 배열을 이용한 단순 스왑핑
 * DFS 활용해서 모든 경우의 수 확인
 * 최대 교환했을시 최댓값 구한다
 */
import java.io.*;
import java.util.*;

public class Solution {

	static char[] arr;
    static int max, chance;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T=Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++)
		{
			st = new StringTokenizer(br.readLine());
			arr= st.nextToken().toCharArray();
            chance = Integer.parseInt(st.nextToken());
            
            max = 0;
            if(arr.length < chance) {
                chance = arr.length;
            }
            dfs(0, 0);
            bw.write("#" + tc + " " + max + "\n");
            
		}
		bw.flush();
		bw.close();
		br.close();

	}
	
	static void dfs(int start, int cnt) {
        if(cnt == chance) {
            String result = "";
            for(char s : arr)
                result += s;
            max = Math.max(max, Integer.parseInt(result));
            return;
        }
        for(int i = start; i<arr.length; i++) {
            for(int j = i+1; j<arr.length; j++){
                char temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                
                dfs(i, cnt +1);
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
    }

}
