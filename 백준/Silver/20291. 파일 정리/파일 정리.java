import java.io.*;
import java.util.*;


public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		TreeMap<String, Integer> map = new TreeMap<>();
		
		for(int i=0; i<N; i++) {
			String file = br.readLine();
			String extension = file.split("\\.")[1];
			map.put(extension, map.getOrDefault(extension, 0) + 1);
		}
		
		for(Map.Entry<String, Integer> result : map.entrySet()) {
			sb.append(result.getKey() + " " + result.getValue() + "\n");
		}
		System.out.println(sb);
	}

}
