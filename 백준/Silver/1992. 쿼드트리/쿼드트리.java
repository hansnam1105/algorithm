import java.io.*;
import java.util.*;

public class Main {
	
	static int[][] map;
	static StringBuilder sb = new StringBuilder();
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			String temp = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = temp.charAt(j) -'0';
			}
		}
		
		QuadTree(0, 0, N);
		
		System.out.println(sb);
		
	}
	
	public static void QuadTree(int x, int y, int size) {
		
		if (check(x,y, size)) {
			sb.append(map[x][y]);
			return;
		}

		
		sb.append("(");
		
		QuadTree(x, y, size/2);						// 왼쪽 위
		QuadTree(x, y + size/2, size/2);				// 오른쪽 위
		QuadTree(x + size/2, y, size/2);				// 왼쪽 아래
		QuadTree(x + size/2, y + size/2, size/2);	// 오른쪽 아래
		
		sb.append(")");
	}
	
	public static boolean check(int x, int y, int size) {
		for(int i=x; i<x+size; i++) {
			for(int j=y; j<y+size; j++) {
				if(!(map[i][j] == map[x][y])){
					return false;
				}
			}
		}
		return true;
	}
}