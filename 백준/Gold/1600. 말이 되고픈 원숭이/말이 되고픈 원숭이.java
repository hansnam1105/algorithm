import java.io.*;
import java.util.*;

public class Main {
    
    static int K, w, h;
    static int[][] map;
    static boolean[][][] visited;
    static int min;

    static int[][] horse = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    static class Monkey {
    	int r, c, count, k;
    	
    	public Monkey(int r, int c, int count, int k) {
    		this.r = r;
    		this.c = c;
    		this.count = count;
    		this.k = k;
    	}
    	
    }
    
    
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new int[h][w];
        for(int i=0; i<h; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<w; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        visited = new boolean[h][w][K+1];
        min = bfs();
        bw.write(min + "\n");
        bw.flush();
        bw.close();
        
        
        
    }
    
    static int bfs() {
    	Queue<Monkey> q = new LinkedList<>();
    	q.offer(new Monkey(0,0,0,K));
    	visited[0][0][K] = true;
    	
    	while(!q.isEmpty()) {
    		Monkey cur = q.poll();
    		if(cur.r == h-1 && cur.c == w-1)
    			return cur.count;
    		            
            for(int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if(nr >= 0 && nc >= 0 && nr < h && nc < w && !visited[nr][nc][cur.k] && map[nr][nc] == 0) {
                    visited[nr][nc][cur.k] = true;
                    q.offer(new Monkey(nr, nc, cur.count + 1, cur.k));
                }
            }
            
            if(cur.k > 0) {
            	for(int i = 0; i < 8; i++) {
                    int nr = cur.r + horse[i][0];
                    int nc = cur.c + horse[i][1];
                    if(nr >= 0 && nc >= 0 && nr < h && nc < w && !visited[nr][nc][cur.k-1] && map[nr][nc] == 0) {
                        visited[nr][nc][cur.k-1] = true;
                        q.offer(new Monkey(nr, nc, cur.count + 1, cur.k - 1));
                    }
                    
                    
                }
            }
    		
    	}
    	return -1;
    	
    }
}