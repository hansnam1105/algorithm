/**
* 퇴실 시간에 10분 추가 및 60분 넘으면 다음 시간
* 입실 시간 기준 정렬
* 우선순위 큐 방 추가 있을시 퇴실시간 현재 입실 시간 비교
* 큐 size
*
*/

import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        int[][] bookTime = new int[book_time.length][2];
        for(int i = 0; i < book_time.length; i++){
            int start = Integer.parseInt(book_time[i][0].replace(":", ""));
            int end = Integer.parseInt(book_time[i][1].replace(":", ""));
            
            end += 10;
            // 60분 넘을시 
            if(end % 100 >= 60){
                end +=40;
            }
            bookTime[i][0] = start;
            bookTime[i][1] = end;
        }
        // 시작 시간 정렬
        Arrays.sort(bookTime, (b1, b2) ->{
            return b1[0]-b2[0];
        });
        // 퇴실시간으로 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        for(int[] reserve : bookTime){
            if(pq.isEmpty()){
                pq.add(reserve);
            }
            else{
                int end = pq.peek()[1];
                
                if(reserve[0] >= end){
                    pq.poll();
                }
                pq.add(reserve);
            }
        }
        answer = pq.size();
        return answer;
    }
}