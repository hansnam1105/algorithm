import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        String[] strNum = new String[numbers.length];
        for(int i=0; i<numbers.length; i++){
            strNum[i] = String.valueOf(numbers[i]);
        }
        
        Arrays.sort(strNum, new Comparator<String>(){
            @Override
            public int compare(String s1, String s2){
                return (s2+s1).compareTo(s1+s2);
            }
        });
        
        if(strNum[0].equals("0")){
            return "0";
        }
        
        StringBuilder sb = new StringBuilder();
        for(String s : strNum){
            sb.append(s);
        }
        return sb.toString();
    }
}