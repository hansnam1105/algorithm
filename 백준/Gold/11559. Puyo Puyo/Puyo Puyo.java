/**
 * Puyo Puyo
 * 같은 색 4개 이상 모이게 되면 터진다
 * bfs? 를 통해 연결된 같은 색 뿌요 확인
 * 뿌요 다 지우고 윗 칸에 있으면 아래로 떨어뜨린다
 * 다시 bfs 진행
 * 1. 4개 이상 뭉쳐있는 노드를 while 문으로 탐색
 * 2. 만약 찾으면 더 주변을 더 탐색한다
 * 3. 터트려서 없앤다
 * 4. 뿌요 떨어뜨리기
 */

import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {

    static int R = 12, C = 6;
    static char[][] map = new char[R][C];
    static boolean[][] visited;
    // 동남서북 3시부터 12시로 탐색
    static int[] dr = { 0, 1, 0, -1 };
    static int[] dc = { 1, 0, -1, 0 };
    //뿌요 탐색 저장
    static ArrayList<Point> puyoList;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }
        // 1. while 문 뿌요 탐색
        int puyopuyo = 0; // 뿌요 연쇄 횟수
        while (true) {
            boolean gameOver = true;
            visited = new boolean[R][C];
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    // 2. while 문 안에서 찾았을 경우
                    if (map[i][j] != '.') {
                        puyoList = new ArrayList<>();
                        bfs(i, j);
                        // 터트려서 없앤다
                        if (puyoList.size() >= 4) {
                            gameOver = false; // 뿌요 찾아서 한 라운드 더
                            for (int k = 0; k < puyoList.size(); k++) {
                                map[puyoList.get(k).x][puyoList.get(k).y] = '.';
                            }
                        } // end if puyoList size
                    } // end if map == '.'
                }
            } // end for 문 탐색
              // 뿌요 더 없을 시 종료
            if (gameOver)
                break;
            // 뿌요 떨어뜨리기
            fallPuyo();
            puyopuyo++;
        }
        bw.write(String.valueOf(puyopuyo));
        bw.flush();
        bw.close();
        
    }
    
    // 탐색 함수 + 삭제할 뿌요 저장
    static void bfs(int r, int c) {
        Queue<Point> q = new LinkedList<>();
        visited[r][c] = true;
        char puyo = map[r][c];
        puyoList.add(new Point(r, c));
        q.offer(new Point(r, c));

        while (!q.isEmpty()) {
            Point cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = cur.x + dr[d];
                int nc = cur.y + dc[d];
                if (isRange(nr, nc) && map[nr][nc] == puyo) {
                    visited[nr][nc] = true;
                    puyoList.add(new Point(nr, nc));
                    q.offer(new Point(nr, nc));
                }
            }
        }
    }
    
    static boolean isRange(int r, int c) {
        return (r >= 0 && r < R && c >= 0 && c < C && !visited[r][c]);
    }

    // 뿌요 떨어뜨리기 함수
    static void fallPuyo() {
        for (int i = 0; i < C; i++) {
            for (int j = R - 1; j > 0; j--) {
                if (map[j][i] == '.') {
                    for (int k = j - 1; k >= 0; k--) {
                        if (map[k][i] != '.') {
                            map[j][i] = map[k][i];
                            map[k][i] = '.';
                            break;
                        }
                    } // 가능한 가장 아래로 내려버리기
                }
            }
        }
    }

}
