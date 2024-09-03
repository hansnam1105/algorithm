import java.io.*;
import java.util.*;

public class Solution {
    
    static int N, M, startRow, startCol, timeLimit, reachableCells;
    static int[][] map;
    static boolean[][] visited;
    
    // 방향 배열: 상, 우, 하, 좌
    static int[][] directions = {{-1,0},{0,1},{1,0},{0,-1}};
    
    // 각 터널 타입별 이동 가능한 방향
    static int[][] tunnelDirections = {
        {},               // 0: 없음
        {0, 1, 2, 3},     // 1: 상, 우, 하, 좌
        {0, 2},           // 2: 상, 하
        {1, 3},           // 3: 우, 좌
        {0, 1},           // 4: 상, 우
        {1, 2},           // 5: 우, 하
        {2, 3},           // 6: 하, 좌
        {0, 3}            // 7: 상, 좌
    };
    
    // Node 클래스: 현재 위치를 나타냄
    static class Node {
        int row, col;
        
        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
        for(int testCase = 1; testCase <= T; testCase++) {
            reachableCells = 1;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            startRow = Integer.parseInt(st.nextToken());
            startCol = Integer.parseInt(st.nextToken());
            timeLimit = Integer.parseInt(st.nextToken());
            
            map = new int[N][M];
            visited = new boolean[N][M];
        
            // 지도 정보 입력
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            // BFS 탐색
            bfs();
            
            // 결과 출력
            bw.write("#" + testCase + " " + reachableCells + "\n");
        }
        bw.flush();
        bw.close();
    }
    
    // BFS 탐색 함수
    static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(startRow, startCol));
        visited[startRow][startCol] = true;
        int currentTime = 1;
        
        if(currentTime == timeLimit) return;
        
        while(!queue.isEmpty()) {
            int currentLevelSize = queue.size();
            
            for(int i = 0; i < currentLevelSize; i++) {
                Node currentNode = queue.poll();
                int currentTunnelType = map[currentNode.row][currentNode.col];

                for(int j = 0; j < tunnelDirections[currentTunnelType].length; j++) {
                    int direction = tunnelDirections[currentTunnelType][j];
                    int nextRow = currentNode.row + directions[direction][0];
                    int nextCol = currentNode.col + directions[direction][1];
                
                    if(isValidMove(nextRow, nextCol)) {
                        // 다음 노드가 반대 방향을 가지고 있는지 확인
                        if(!canMove(nextRow, nextCol, (direction + 2) % 4)) continue;
                        if(visited[nextRow][nextCol]) continue;

                        visited[nextRow][nextCol] = true;
                        reachableCells++;
                        queue.add(new Node(nextRow, nextCol));
                    }
                }
            }
            if(++currentTime == timeLimit) return;
        }
    }
    
    // 해당 방향으로 이동 가능한지 확인하는 함수
    static boolean canMove(int row, int col, int direction) {
        int nextTunnelType = map[row][col];
        for(int d : tunnelDirections[nextTunnelType]) {
            if(d == direction) 
                return true;
        }
        return false;
    }
    
    // 주어진 좌표가 유효한지 확인하는 함수
    static boolean isValidMove(int row, int col) {
        return (row >= 0 && row < N && col >= 0 && col < M && map[row][col] != 0);
    }
}
