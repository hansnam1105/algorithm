/**
 * 백준 상어 초등학교
 * 
 */

import java.io.*;
import java.util.*;

public class Main {
	
	// 학생 자리 정보 삽입
	static class Seat{
		int r, c;
		int emptyCnt;
		int friendCnt;
		
		public Seat(int r, int c, int emptyCnt, int friendCnt) {
			this.r = r;
			this.c = c;
			this.emptyCnt = emptyCnt;
			this.friendCnt = friendCnt;
		}
	}
	
	static int N;
	static int[][] room;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static HashMap<Integer, int[]> likeMap;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		room = new int[N][N];
		int total = N*N;
		
		// HashMap을 통해 key : 학생의 번호 value : 좋아하는 학생 번호 array 삽입
		likeMap = new HashMap<>();
		for(int i=0; i<total; i++) {
			st = new StringTokenizer(br.readLine());
			int[] fav = new int[4];
			int student=0;
			for(int j=0; j<5; j++) {
				if(j==0) {
					student = Integer.parseInt(st.nextToken());
				} else {
					fav[j-1] = Integer.parseInt(st.nextToken());
				}
			}
			likeMap.put(student, fav);
			
			// 의자 자리 배치
			searchSeat(student);			
		}
		bw.write(String.valueOf(getScore()));
        bw.flush();
        bw.close();
	}
	
	
	
	public static void searchSeat(int student) {
		int[] favorites = likeMap.get(student);
		ArrayList<Seat> seats = new ArrayList<>();
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				
				int emptyCnt = 0; // 주변 빈 의자 수
				int friendCnt = 0; // 주변 친구 수
				
				for(int d=0; d<4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(checkRange(nr, nc)) {
						continue;
					}
					
					if(room[nr][nc] == 0) {
						emptyCnt++;
						continue;
					} 
					for(int i=0; i<4; i++) {
						if(favorites[i] == room[nr][nc]) {
							friendCnt++;
						}
					}
				}
				// 의자 수 추가
				seats.add(new Seat(r, c, emptyCnt, friendCnt));
				
			}
		}
		
		seats.sort(new Comparator<Seat>() {
			/*
			 * 1. 가장 좋아하는 학생수
			 * 2. 1 만족시, 비어있는 칸 많은 수
			 * 3. 1, 2, 만족시 가장 작은 행 가장 작은 열
			 */
			@Override
			public int compare(Seat o1, Seat o2) {
				if(o1.friendCnt == o2.friendCnt) {
					if(o1.emptyCnt == o2.emptyCnt) {
						if (o1.r == o2.r) {
		                    return o1.c - o2.c; // 작은
		                }
		                return o1.r - o2.r; // 작은
					}
					return o2.emptyCnt - o1.emptyCnt; // 더 많은
				}
				return o2.friendCnt - o1.friendCnt; // 더 많은
			}
		});
		
		// 학생 자리 배치
		for(Seat s : seats) {
			if(room[s.r][s.c] != 0) {
				continue;
			}
			room[s.r][s.c]= student; 
			return;
		}
	}
	
	// 만족도 계산
	public static int getScore() {
		int score = 0;
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				int friendCnt = 0; // 주변 친구 수
				int[] favorites = likeMap.get(room[r][c]);
				
				for(int d=0; d<4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(checkRange(nr, nc)) {
						continue;
					} 
					for(int i=0; i<4; i++) {
						if(favorites[i] == room[nr][nc]) {
							friendCnt++;
						}
					}
				}
				
				if(friendCnt == 4) {
					score += 1000;
				}
				else if(friendCnt == 3) {
					score += 100;
				}
				else if(friendCnt == 2) {
					score += 10;
				}
				else if(friendCnt == 1) {
					score += 1;
				}
				
			
			}
		}
				
		
		return score;
	}
	
	public static boolean checkRange(int nr, int nc) {
		if(nr<0 || nr>=N || nc<0 || nc>=N) {
			return true;
		}
		return false;
	}
	
	
	
	

}
