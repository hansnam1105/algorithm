import java.io.*;
import java.util.*;

public class Main {
	
	static int[][] sudoku = new int[9][9];
	static List<int[]> emptyCells = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<9; i++) {
			char[] c = br.readLine().toCharArray();
			for(int j=0; j<9; j++) {
				sudoku[i][j] = c[j] - '0';
				if(sudoku[i][j] == 0) {
					emptyCells.add(new int[] {i,j});
				}
			}
		}
		
		solve(0);
		
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				sb.append(sudoku[i][j]);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
			
	}
	
	static boolean solve(int idx) {
		if(idx == emptyCells.size()) {
			return true;
		}
		
		int[] cell = emptyCells.get(idx);
		int r = cell[0];
		int c = cell[1];
		
		for(int i=1; i<=9; i++) {
			if(isValid(r, c, i)) {
				sudoku[r][c] = i;
				if(solve(idx+1)) {
					return true;
				}
				sudoku[r][c] = 0;
			}
		}
		
		return false;
		
	}
	
	static boolean isValid(int row, int col, int num) {
		for(int i=0; i<9; i++) {
			if(sudoku[row][i] == num) return false;
		}
		
		for(int i=0; i<9; i++) {
			if(sudoku[i][col] == num) return false;
		}
		
		int startRow = (row/3) * 3;
		int startCol = (col/3) * 3;
		for(int i=startRow; i<startRow + 3; i++) {
			for(int j=startCol; j<startCol+3; j++) {
				if(sudoku[i][j] == num) return false;
			}
		}
		
		return true;
	}

}
