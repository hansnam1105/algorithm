import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main {
	
	static int N, M, R;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        R = parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = parseInt(st.nextToken());
            }
        }
       
        
        int menu = 0;

        // 회전 횟수에 대해 반복
        st = new StringTokenizer(br.readLine());
        for (int r = 0; r < R; r++) {
            menu = parseInt(st.nextToken());
            N = map.length;
            M = map[0].length;
            switch (menu) {
            case 1:
            	updown();
            	break;
            case 2 :
            	leftRight();
            	break;
            case 3 :
            	rotateR90();
            	break;
            case 4 :
            	rotateL90();
            	break;
            case 5 :
            	changeSection1();
            	break;
            case 6 :
            	changeSection2();
            	break;
            }
        }
        
        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
        
	}
	// 1번 연산 상하 반전
	public static void updown() {
		int[][] newMap = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				newMap[i][j] = map[N-i-1][j];
			}
		}
		map = newMap.clone();
		
	}
	// 2번 연산 좌우 반전
	public static void leftRight() {
		int[][] newMap = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				newMap[i][j] = map[i][M-j-1];
			}
		}
		map = newMap.clone();
		
	}
	// 3번 연산 오른쪽 90도 회전
	public static void rotateR90() {
		int[][] newMap = new int[M][N];
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				newMap[i][j] = map[N - j - 1][i];
			}
		}
		map = newMap.clone();
		
	}
	// 4번 연산 왼쪽 90도 회전
	public static void rotateL90() {
		int[][] newMap = new int[M][N];
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				newMap[i][j] = map[j][M - 1 - i];
			}
		}
		map = newMap.clone();
		
	}
	// 5번 연산 1->2 / 2->3 / 3-> 4 / 4->1 이동 연산
	public static void changeSection1() {
	    int[][] newMap = new int[N][M];
	    int halfN = N / 2;
	    int halfM = M / 2;

	    // 1번 섹션을 2번 섹션으로 이동
	    for (int i = 0; i < halfN; i++) {
	        for (int j = 0; j < halfM; j++) {
	            newMap[i][j + halfM] = map[i][j];
	        }
	    }

	    // 2번 섹션을 3번 섹션으로 이동
	    for (int i = 0; i < halfN; i++) {
	        for (int j = halfM; j < M; j++) {
	            newMap[i + halfN][j] = map[i][j];
	        }
	    }

	    // 3번 섹션을 4번 섹션으로 이동
	    for (int i = halfN; i < N; i++) {
	        for (int j = halfM; j < M; j++) {
	            newMap[i][j - halfM] = map[i][j];
	        }
	    }

	    // 4번 섹션을 1번 섹션으로 이동
	    for (int i = halfN; i < N; i++) {
	        for (int j = 0; j < halfM; j++) {
	            newMap[i - halfN][j] = map[i][j];
	        }
	    }

	    map = newMap.clone();
	}

	// 6번 연산 1->4 / 4->3 / 3->2 / 2->1 이동 연산
	public static void changeSection2() {
	    int[][] newMap = new int[N][M];
	    int halfN = N / 2;
	    int halfM = M / 2;

	    // 1번 섹션을 4번 섹션으로 이동
	    for (int i = 0; i < halfN; i++) {
	        for (int j = 0; j < halfM; j++) {
	            newMap[i + halfN][j] = map[i][j];
	        }
	    }

	    // 4번 섹션을 3번 섹션으로 이동
	    for (int i = halfN; i < N; i++) {
	        for (int j = 0; j < halfM; j++) {
	            newMap[i][j + halfM] = map[i][j];
	        }
	    }

	    // 3번 섹션을 2번 섹션으로 이동
	    for (int i = halfN; i < N; i++) {
	        for (int j = halfM; j < M; j++) {
	            newMap[i - halfN][j] = map[i][j];
	        }
	    }

	    // 2번 섹션을 1번 섹션으로 이동
	    for (int i = 0; i < halfN; i++) {
	        for (int j = halfM; j < M; j++) {
	            newMap[i][j - halfM] = map[i][j];
	        }
	    }

	    map = newMap.clone();
	}


}
