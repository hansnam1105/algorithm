class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        int[] videoLen = new int[2];
        videoLen[0] = Integer.parseInt(video_len.split(":")[0]);
        videoLen[1] = Integer.parseInt(video_len.split(":")[1]);

        int[] posTime = new int[2];
        posTime[0] = Integer.parseInt(pos.split(":")[0]);
        posTime[1] = Integer.parseInt(pos.split(":")[1]);

        int[] opStart = new int[2];
        opStart[0] = Integer.parseInt(op_start.split(":")[0]);
        opStart[1] = Integer.parseInt(op_start.split(":")[1]);

        int[] opEnd = new int[2];
        opEnd[0] = Integer.parseInt(op_end.split(":")[0]);
        opEnd[1] = Integer.parseInt(op_end.split(":")[1]);

        // 오프닝 구간 체크 함수
        posTime = skipOpening(posTime, opStart, opEnd);

        for(String command : commands){
            switch(command) {
                case "next" : 
                    posTime[1] += 10;
                    if(posTime[1] >= 60) {
                        posTime[0]++;
                        posTime[1] -= 60;
                    }
                    if(posTime[0] > videoLen[0] || (posTime[0] == videoLen[0] && posTime[1] > videoLen[1])) {
                        posTime[0] = videoLen[0];
                        posTime[1] = videoLen[1];
                    }
                    break;
                    
                case "prev" :
                    posTime[1] -= 10;
                    if(posTime[1] < 0) {
                        posTime[0]--;
                        posTime[1] += 60;
                    }
                    if(posTime[0] < 0) {
                        posTime[0] = 0;
                        posTime[1] = 0;
                    }
                    break;
            }
            // 오프닝 구간을 벗어나도록 다시 체크
            posTime = skipOpening(posTime, opStart, opEnd);
        }

        // 답 형식 맞추기
        answer = formatTime(posTime);

        return answer;
    }
    
    // 오프닝 구간을 넘기는 함수
    private int[] skipOpening(int[] posTime, int[] opStart, int[] opEnd) {
        if((posTime[0] > opStart[0] || (posTime[0] == opStart[0] && posTime[1] >= opStart[1])) 
           && (posTime[0] < opEnd[0] || (posTime[0] == opEnd[0] && posTime[1] < opEnd[1]))) {
            posTime[0] = opEnd[0];
            posTime[1] = opEnd[1];
        }
        return posTime;
    }

    // 시간을 "00:00" 형식으로 반환하는 함수
    private String formatTime(int[] posTime) {
        String hour = (posTime[0] < 10) ? "0" + posTime[0] : String.valueOf(posTime[0]);
        String minute = (posTime[1] < 10) ? "0" + posTime[1] : String.valueOf(posTime[1]);
        return hour + ":" + minute;
    }
}
