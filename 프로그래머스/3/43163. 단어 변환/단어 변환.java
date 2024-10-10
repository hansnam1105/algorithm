import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        for(String word : words){
            if(target.equals(word)){
                return bfs(begin, target, words);
            }
        }
        return answer;
    }
    
    private int bfs(String begin, String target, String[] words){
        Queue<String> queue = new LinkedList<>();
        queue.add(begin);
        boolean[] visited = new boolean[words.length];
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            count++;
            
            for (int i = 0; i < size; i++) {
                String now = queue.poll();
                
                for (int j = 0; j < words.length; j++) {
                    if (!visited[j] && charDiff(now, words[j])) {
                        if (words[j].equals(target)) {
                            return count;
                        }
                        queue.add(words[j]);
                        visited[j] = true;
                    }
                }
            }
        }
        return 0;
    }
    
    private boolean charDiff(String now, String word){
        int diffChar = 0;
        for (int i = 0; i < word.length(); i++) {
            if (now.charAt(i) != word.charAt(i)) {
                diffChar++;
            }
        }
        return diffChar == 1;
    }
}