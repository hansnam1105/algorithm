import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int[][] exhaust = new int[][]{{1,1,1}, {5,1,1}, {25, 5, 1}};
        int totalPicks = Arrays.stream(picks).sum();
        ArrayList<Mining> list = new ArrayList<>();
        for(int i=0; i<minerals.length; i+=5){
            if(totalPicks == 0) break;
            
            int dia = 0, iron = 0, stone = 0;
            for(int j=i; j<i+5; j++){
                if(j == minerals.length) break;
                String mineral = minerals[j];
                int val = 0;
                switch (mineral){
                    case "iron" :
                        val = 1;
                        break;
                    case "stone" :
                        val = 2;
                        break;
                    default :
                        val = 0;
                        break;
                }
                dia += exhaust[0][val];
                iron += exhaust[1][val];
                stone += exhaust[2][val];
            }
            list.add(new Mining(dia, iron, stone));
            totalPicks--;
        }
        
        Collections.sort(list);
        for(Mining m : list){
            int dia = m.dia;
            int iron = m.iron;
            int stone = m.stone;
            
            if(picks[0] > 0){
                answer += dia;
                picks[0]--;
                continue;
            }
            if(picks[1] > 0){
                answer += iron;
                picks[1]--;
                continue;
            }
            if(picks[2] > 0){
                answer += stone;
                picks[2]--;
                continue;
            }
        }
        
        return answer;
    }
    
    static class Mining implements Comparable<Mining> {
        int dia, iron, stone;
        
        public Mining(int dia, int iron, int stone){
            this.dia = dia;
            this.iron = iron;
            this.stone = stone;
        }
        
        @Override
        public int compareTo(Mining m){
            return m.stone - this.stone;
        }
    }
}