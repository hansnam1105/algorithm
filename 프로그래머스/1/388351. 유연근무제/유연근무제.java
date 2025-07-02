class Solution {
    private int addMinutes(int time, int minutesToAdd) {
        int hour = time / 100;
        int minute = time % 100;

        minute += minutesToAdd;

        hour += minute / 60;
        minute %= 60;

        return hour * 100 + minute;
    }

    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        int employeeCount = schedules.length;

        for (int i = 0; i < employeeCount; i++) {
            boolean isQualified = true;
            
            int deadline = addMinutes(schedules[i], 10);

            for (int j = 0; j < 7; j++) {
                int currentDayOfWeek = ((startday - 1 + j) % 7) + 1;

                if (currentDayOfWeek <= 5) {
                    int arrivalTime = timelogs[i][j];
                    
                    if (arrivalTime > deadline) {
                        isQualified = false;
                        break;
                    }
                }
            }

            if (isQualified) {
                answer++;
            }
        }
        
        return answer;
    }
}