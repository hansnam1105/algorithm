import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> userMap = new HashMap<>();
        
        List<String[]> messageAction = new ArrayList<>();
        
        for(String rec : record){
            String[] parts = rec.split(" ");
            String command = parts[0];
            String id = parts[1];
            
            if(command.equals("Enter")){
                String nickname = parts[2];
                userMap.put(id, nickname);
                messageAction.add(new String[]{"Enter", id});
            }
            else if(command.equals("Leave")){
                messageAction.add(new String[]{"Leave", id});
            }
            else if(command.equals("Change")){
                String nickname = parts[2];
                userMap.put(id, nickname);
            }
        }
        
        String[] answer = new String[messageAction.size()];
        
        for(int i=0; i<messageAction.size(); i++){
            String[] actions = messageAction.get(i);
            String command = actions[0];
            String id = actions[1];
            String findNickname = userMap.get(id);
            
            if(command.equals("Enter")){
                answer[i] = findNickname + "님이 들어왔습니다.";
            }
            else if(command.equals("Leave")){
                answer[i] = findNickname + "님이 나갔습니다.";
            }
        }
        return answer;
    }
}