import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	/**
	 * 입력 : N : 종이 한변의 길이 2, 4, 8, 16, 32 ,64 128 N*N 배열 입력 하얀색 : 0 / 파란색 : 1 종이
	 * 자르기는 N/2로만 일단 전체가 파란색이거나 흰색 아니면 나눔
	 * 
	 * @param args
	 * @return 하얀색 색종이와 파란색 색종이 개수
	 */
	public static int N;
	public static int[][] map;
	static int white = 0;
	static int blue = 0;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		check(0,0, N);
		System.out.println(white);
		System.out.println(blue);
		
		

	}

	public static void check(int x, int y, int size) {
		//기존 코드 오류 - 대각 탐색만 하게됨
//		boolean color = true;
//		exit_For: for(int i=0; i<size; i++) {
//			for(int j=0; j<size; j++) {
//				if(map[x][y] != map[x+i][y+i]) {
//					color = false;
//					break exit_For;
//				}		
//			}
//		}
		
		if(singleColor(x,y,size)) {
			if(map[x][y] == 0) {
				white++;
			}else {
				blue++;
			}
		}else {
			check(x, y, size/2);
			check(x+size/2, y, size/2);
			check(x, y + size/2, size/2);
			check(x+size/2, y+size/2, size/2);
			
		}
	}
	public static boolean singleColor(int x, int y, int size) {
		int color = map[x][y];
		for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (color != map[x + i][y + j]) {
                    return false;
                }
            }
        }
        return true;
	}
}