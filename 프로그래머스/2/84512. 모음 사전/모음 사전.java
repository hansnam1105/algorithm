import java.util.*;
class Solution {
    
    private char[] arr = {'A', 'E', 'I', 'O', 'U'};
    List<String> list = new ArrayList<>();
    
    public int solution(String word) {
        int answer = 0;
        
        findWord("");
        
        return list.indexOf(word);
    }
    
    private void findWord(String word){
        if(word.length() > 5){
            return;
        }
        
        list.add(word);
        
        for(int i=0; i<5; i++){
            findWord(word + arr[i]);
        }
    }
}