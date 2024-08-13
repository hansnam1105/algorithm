/**
 * 해당 코드는 나중에 다시 풀어볼것 - 이미 다시 풀것 체크해놨음
 * 코드는 스스로 풀기에 PriorityQueue 활용과 알고리즘이 막혀 검색 후 작성
 * 5653. [모의 SW 역량테스트] 줄기세포배양
 * 내가 해낸 부분
 * - 세포 정보 관리용 클래스
 * - 번식때 사용할 번식 여부 체크용 배열 visited
 * - BFS 베이스 메서드 simulation 탐색 조건들
 * 
 * 도움 받은 부분
 * - 시간마다 상태 정보 변경 메서드(step)를 세포 정보에 만드는것
 * - Queue 말고 PriorityQueue 활용 및 생명력 높은 순을 위한
 * - Comparator 넣어주기
 * - 세포 배양 위치를 중앙으로 잡아주는 방법
 * 
 * 해당 문제는 금요일 저녁 다시 도전해볼 예정
 */

import java.util.*;
import java.io.*;

public class Solution {

    static int N, M, K;
    static boolean[][] visited;
    
    // 세포 정보 관리용 클래스
    static class Cell {
        int x, y, life, time;
        int state; // 상태 정보 0: 비활성, 1:활성, 2:죽은 세포

        public Cell(int x, int y, int life) {
            this.x = x;
            this.y = y;
            this.life = life;
            this.time = life; // 생명력 수치로 셋팅
        }
        
        // 시간마다 상태 정보 변경하는 메서드
 		void step() {
 			switch (this.state) {
 			case 0:// 비활성 상태
 				if (--time == 0) { // 생명력이 0이 되면? 활성화 상태로 바꿔주기
 					state = 1;
 				}
 				break;
 			case 1: // 활성 상태
 				if (++time == life) { // 생명력과 같아지면? 죽은 세포로 바꿔주기
 					state = 2;
 				}
 				break;
 			}
 		}

    }

    static PriorityQueue<Cell> pq; //생명력이 높은 순서로 정렬할 우선순위 큐
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<Cell> q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            // !활성화된 세포를 담을 우선순위 큐 생성
            // !정렬 기준은 생명력 높은 순 -> Comparator 넣어주기
            pq = new PriorityQueue<>(new Comparator<Cell>() {
            	
            	@Override
            	public int compare(Cell o1, Cell o2) {
            		return o2.life - o1.life;
            	}
            });
            
            q = new LinkedList<>();
            
            visited = new boolean[N + K + 1][M + K + 1]; 
            
            // 세포 배양 정보 받기, 세포 배양 위치를 중앙으로 잡아주기 - 핵심
            for (int r = K / 2 + 1; r < N + K / 2 + 1; r++) {
               st = new StringTokenizer(br.readLine());
               for (int c = K / 2 + 1; c < M + K / 2 + 1; c++) {
                  // 이 위치에 있는 세포의 생명력
                  int tmp = Integer.parseInt(st.nextToken());
                  if (tmp != 0) {
                     //초기 세포들 모두 큐에 담아주기
                     visited[r][c] = true;
                     q.add(new Cell(r, c, tmp)); // 초기 세포 정보 큐에 넣어주기
                  }
               }
            }
            
            simulate();
            sb.append("#").append(tc).append(" ");
            sb.append(q.size());
            sb.append("\n");
        }
        System.out.println(sb);
    }

    // BFS 베이스
    public static void simulate() {

        while (K-- > 0) {
        	// 이번 시간에 확인할 세포 수
        	int size = q.size();
        	
        	for(int s=0; s<size; s++) {
        		Cell c = q.poll();
        		
        		// 세포가 활성화 상태면 번식을 위해 우선순위 큐에 담아주기
        		// 기존에는 Queue에 담았다
        		if(c.state == 1) {
        			pq.add(c);
        		}
        		
                // 세포 상태 변화 시키기
        		c.step();
        		if(c.state == 2) { // 죽은 세포
        			continue;
        		}
        		
        		// 활성화, 비활성화 상태의 세포는 다음 시간에 배양할 수 있게
                // 큐에 넣어 주기
        		q.add(c);
        	}

        	// 세포 번식
            while (!pq.isEmpty()) {
                Cell cur = pq.poll();
                
                for(int d=0; d<4; d++) {
                	int nx = cur.x + dx[d];
                	int ny = cur.y + dy[d];
                	/*
                	 * 검색으로 알게 된 부분
                	 * 이미 방문 처리 된 경우는, 이전에 배양 된 자리거나.
                	 * 더 생명력이 높은 세포가 배양 되어 있는 경우
                	 */
                	if(visited[nx][ny]) continue;
                	//가장 먼저 뽑혀 나온 경우 --> 생명력이 가장 높은 경우 
                    //다음에 같은 자리 뽑혀 나오는 상황 고려 안해도 됨
                	visited[nx][ny] = true;
                	//다음 시간에 세포 배양할 수 있게 큐에 넣어주기
                	q.add(new Cell(nx, ny, cur.life));
                }

                
            }

        }

    }
}