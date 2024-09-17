import java.io.*;

public class Main {
    static int N;
    static int[] board;
    static int result = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        
        board = new int[N];
        
        nQueen(0);
        
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
    
    static void nQueen(int row) {
        if(row == N) {
            result++;
            return;
        }
        
        for(int col = 0; col < N; col++){
            if(isAble(row, col)){
                board[row] = col;
                nQueen(row + 1);
            }
        }
    }
    
    static boolean isAble(int row, int col){
        for(int i = 0; i < row; i++){
            // 같은 열 & 대각선에 퀸 있는 경우
            if(board[i] == col){
                return false;
            }
            if(Math.abs(board[i] - col) == Math.abs(i - row)){
                return false;
            }
        }
        return true;
    }
}