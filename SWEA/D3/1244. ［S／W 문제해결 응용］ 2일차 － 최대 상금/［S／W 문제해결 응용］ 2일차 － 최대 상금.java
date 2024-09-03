import java.util.*;

public class Solution {

	static String[] arr;
    static int max, chance;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			arr= sc.next().split("");
            chance = sc.nextInt();
            
            max = 0;
            if(arr.length < chance) {
                chance = arr.length;
            }
            dfs(0, 0);
            System.out.println("#" + test_case + " " + max);
            
		}

	}
	
	static void dfs(int start, int cnt) {
        if(cnt == chance) {
            String result = "";
            for(String s : arr)
                result += s;
            max = Math.max(max, Integer.parseInt(result));
            return;
        }
        for(int i = start; i<arr.length; i++) {
            for(int j = i+1; j<arr.length; j++){
                String temp = arr[i];
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
