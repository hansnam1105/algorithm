class Solution {
    public int solution(String s) {
        int answer = s.length();
        for(int u=1; u<=s.length() / 2; u++){
            StringBuilder sb = new StringBuilder();
            String prev = "";
            int count = 1;
            
            for(int i=0; i<s.length(); i+=u){
                String current;
                if(i+u <= s.length()){
                    current = s.substring(i, i+u);
                } else {
                    current = s.substring(i);
                }
                
                if(i==0){
                    prev = current;
                } else{
                    if(prev.equals(current)){
                        count++;
                    } else{
                        if(count > 1){
                            sb.append(count);
                        }
                        sb.append(prev);
                        prev = current;
                        count = 1;
                    }
                }
            }
            if(count > 1){
                sb.append(count);
            }
            sb.append(prev);
            
            answer = Math.min(answer, sb.length());
        }
        return answer;
    }
}