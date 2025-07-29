class Solution {
    private int answer;
    public int solution(int[] numbers, int target) {
        answer = 0;
        
        powerSet(numbers, target ,0 ,0);
        
        return answer;
    }
    
    private void powerSet(int[] numbers, int target, int sum, int index){
        if(index == numbers.length){
            if(sum == target){
                answer++;
            }
            return;
        }
        
        powerSet(numbers, target, sum+numbers[index], index+1);
        powerSet(numbers, target, sum-numbers[index], index+1);
    }
}