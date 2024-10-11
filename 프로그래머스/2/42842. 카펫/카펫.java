class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        int area = brown + yellow;
        int start = 3;
        int end = area / 3;
        
        for(int w = start; w <= end; w++){
            for(int h = start; h<= end; h++){
                if((w*h == area) && (w-2)*(h-2) == yellow && w>=h){
                    answer[0] = w;
                    answer[1] = h;
                    return answer;
                }
            }
        }
        
        
        return answer;
    }
    
    
    
}