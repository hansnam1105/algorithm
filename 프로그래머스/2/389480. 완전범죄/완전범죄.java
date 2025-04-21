import java.util.*;

class Solution {
    
    private int result = Integer.MAX_VALUE;
    
    public int solution(int[][] info, int n, int m) {
        Set<String> visited = new HashSet<>();
        dfs(info, 0, 0, 0, n, m, visited);
        
        
        return result == Integer.MAX_VALUE ? -1 : result;
    }
    
    private void dfs(int[][] info,
                     int aTrack, int bTrack, int idx,
                     int n, int m, Set<String> visited){
        if(idx == info.length){
            if(aTrack < n && bTrack < m){
                result = Math.min(result, aTrack);
            }
            return;
        }
        
        String key = aTrack + "," + bTrack + "," + idx;
        if(visited.contains(key)){
            return;
        }
        visited.add(key);
        
        if(aTrack + info[idx][0] < n){
            dfs(info, aTrack + info[idx][0], bTrack, idx+1, n, m, visited);
        }
        
        if(bTrack + info[idx][1] < m){
            dfs(info, aTrack, bTrack + info[idx][1], idx+1, n, m, visited);
        }
        
    }
}