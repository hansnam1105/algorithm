import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {

	static int N,M,result;
	static boolean[] visited;
	static int value;
	static ArrayList<Point> chicken;
	static ArrayList<Point> house;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		chicken = new ArrayList<>();
		house = new ArrayList<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				value = Integer.parseInt(st.nextToken());
				if(value == 2) {
					chicken.add(new Point(i, j));
				}
				else if(value == 1) {
					house.add(new Point(i, j));
				}
				
			}
		}
		visited = new boolean[chicken.size()];

		result = Integer.MAX_VALUE;
		dfs(0, 0, new Point[M]);
		
		
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void dfs(int depth, int start,  Point[] selected) {
		if(depth == M) {
			int total = 0;
			for(Point p : house) {
				int minDist = Integer.MAX_VALUE;
				for(Point c : selected) {
					int distance = distance(p, c);
					minDist = Math.min(minDist, distance);
				}
				total += minDist;
			}
			
			result = Math.min(result, total);
			return;

		}
		
		
		for (int i = start; i < chicken.size(); i++) {
            selected[depth] = chicken.get(i);
            dfs(depth + 1, i + 1, selected);
        }
	}
	
	public static int distance(Point p1, Point p2) {
		return Math.abs(p1.y - p2.y) + Math.abs(p1.x - p2.x);
	}

}
