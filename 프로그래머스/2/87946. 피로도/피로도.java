class Solution {
    private int length, answer;
    private boolean[] visited;
    public int solution(int k, int[][] dungeons) {
        answer = -1;
        length = dungeons.length;
        visited = new boolean[length];
        dfs(0, k, dungeons);
        
        return answer;
    }
    
    private void dfs(int depth, int k, int[][] dungeons){
        if(k<=0){
            return;
        }
        for(int i=0; i<length; i++){
            if(visited[i] || dungeons[i][0] > k)
                continue;
            visited[i] = true;
            dfs(depth+1, k-dungeons[i][1], dungeons);
            visited[i] = false;
        }
        answer = Math.max(answer, depth);
    }
}