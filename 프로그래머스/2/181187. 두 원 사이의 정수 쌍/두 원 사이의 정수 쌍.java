/*
* 한 사분면에 있는 점 개수 * 4 + x,y 선에 있는 점수
* 큰원에 있는 점 - 작은 원에 있는 점
* x^2 + y^2 = r^2
* y^2 = r^2 - x^2;
* y = sqrt(r^2 - x^2)
*/

import java.util.*;

class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        long r1power2 = (long)Math.pow(r1, 2);
        long r2power2 = (long)Math.pow(r2, 2);
        long line = 0;
        for(int i=0; i<=r2; i++){
            long y2 = (long) Math.sqrt(r2power2 - (long)Math.pow(i,2));
            long y1 = (long) Math.sqrt(r1power2 - (long)Math.pow(i,2));
            answer += (y2-y1) * 4;
            
            if(Math.sqrt((r1power2 - Math.pow(i,2)))%1 == 0){
                line++;
            }
        }
        
        answer += line*4-4; 
        
        return answer;
    }
}