/**
 * 추억의 2048 게임
 * 확실히 구현 문제는 어렵다.
 * 조건이 많기도 하고 코드를 짜면서도 이게 맞나 생각일 들때도 있다.
 * 
 */

import java.io.*;
import java.util.*;

public class Solution {

	static int[][] map;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        
        for(int tc = 1; tc <= T; tc++) {
        	st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	String command = st.nextToken();
        	map = new int[N][N];
        	
        	for(int i=0; i<N; i++) {
            	st = new StringTokenizer(br.readLine());
        		for(int j=0; j<N; j++) {
        			map[i][j] = Integer.parseInt(st.nextToken());
        		}
        	}
        	
        	switch(command) {
        	case "right":
        		right();
        		break;
        	case "left" :
        		left();
        		break;
        	case "up" :
        		up();
        		break;	
        	case "down" :
        		down();
        		break;
        	}
        	
        	sb.append("#").append(tc).append("\n");
        	for(int i=0; i<N; i++) {
        		for(int j=0; j<N; j++) {
        			sb.append(map[i][j] + " ");
        		}
        		sb.append("\n");
        	}
        }
        System.out.println(sb);
	}
	// 오른쪽 이동
	public static void right() {
		for(int r=0; r<N; r++) {
			List<Integer> list = new ArrayList<>();
			for(int c=N-1; c>=0; c--) {
				if(map[r][c] != 0) list.add(map[r][c]);
			}
			for(int i=0; i<list.size()-1; i++) {
				if(list.get(i).equals(list.get(i+1))) {
					list.set(i, list.get(i)*2);
					list.remove(i+1);
					list.add(0);
				}
			}
			for (int c=N-1, i=0; c>=0; c--, i++) {
	            if (i<list.size()) map[r][c] = list.get(i);
	            else map[r][c] = 0;
	        }
			
		}
	}
	
	// 왼쪽 이동
	public static void left() {
		for(int r=0; r<N; r++) {
			List<Integer> list = new ArrayList<>();
			for(int c=0; c<N; c++) {
				if(map[r][c] != 0) list.add(map[r][c]);
			}
			for(int i=0; i<list.size()-1; i++) {
				if(list.get(i).equals(list.get(i+1))) {
					list.set(i, list.get(i)*2);
					list.remove(i+1);
					list.add(0);
				}
			}
			for (int c=0, i=0; c<N; c++, i++) {
	            if (i<list.size()) map[r][c] = list.get(i);
	            else map[r][c] = 0;
	        }
			
		}
	}
	
	
	// 위쪽 이동
		public static void up() {
			for(int c=0; c<N; c++) {
				List<Integer> list = new ArrayList<>();
				for(int r=0; r<N; r++) {
					if(map[r][c] != 0) list.add(map[r][c]);
				}
				for (int i=0; i<list.size()-1; i++) {
		            if(list.get(i).equals(list.get(i+1))) {
		            	list.set(i, list.get(i) * 2);
		            	list.remove(i+1);
		            	list.add(0);
		            }
		        }
		        for (int r=0, i=0; r<N; r++, i++) {
		            if (i<list.size()) map[r][c] = list.get(i);
		            else map[r][c] = 0;
		        }
			}
		}
	
	
	// 아래쪽 이동
	public static void down() {
		for(int c=0; c<N; c++) {
			List<Integer> list = new ArrayList<>();
			for(int r=N-1; r>=0; r--) {
				if(map[r][c] != 0) list.add(map[r][c]);
			}
			for (int i=0; i<list.size()-1; i++) {
	            if(list.get(i).equals(list.get(i+1))) {
	            	list.set(i, list.get(i) * 2);
	            	list.remove(i+1);
	            	list.add(0);
	            }
	        }
	        for (int r=N-1, i= 0; r>=0; r--, i++) {
	            if (i<list.size()) map[r][c] = list.get(i);
	            else map[r][c] = 0;
	        }
		}
	}

}
