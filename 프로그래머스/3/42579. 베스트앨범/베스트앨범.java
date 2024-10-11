import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        Map<String, HashMap<Integer, Integer>> music = new HashMap<>();
        HashMap<String, Integer> rank = new HashMap<>();
        for(int i=0; i<genres.length; i++){
            if(!music.containsKey(genres[i])){
                HashMap<Integer, Integer> map = new HashMap<>();
                map.put(i, plays[i]);
                music.put(genres[i], map);
                rank.put(genres[i], plays[i]);
            } else{
                music.get(genres[i]).put(i, plays[i]);
                rank.put(genres[i], rank.get(genres[i]) + plays[i]);
            }
        }
        
        List<String> keySet = new ArrayList(rank.keySet());
        Collections.sort(keySet, (m1, m2) -> rank.get(m2) - rank.get(m1));
        
        for(String key : keySet) {
            HashMap<Integer, Integer> map = music.get(key);
            
            List<Integer> musicSet = new ArrayList(map.keySet());
            Collections.sort(musicSet, (m1, m2) -> map.get(m2) - map.get(m1));
            
            answer.add(musicSet.get(0));
            if(musicSet.size()>1){
                answer.add(musicSet.get(1));
            }
            
        }
        return answer.stream().mapToInt(i -> i).toArray();
    }
}