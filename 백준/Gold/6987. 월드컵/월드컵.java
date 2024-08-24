/**
 * 월드컵
 * 각 조 6팀 -> 15개의 경기
 * 
 */
import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main {

    static int[] wins, draws, losses;
    static int[][] matches;
    static boolean[] result = new boolean[4];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        // 15개의 경기를 설정합니다.
        int matchIndex = 0;
        matches = new int[15][2];
        for (int i = 0; i < 6; i++) {
            for (int j = i + 1; j < 6; j++) {
                matches[matchIndex][0] = i;
                matches[matchIndex][1] = j;
                matchIndex++;
            }
        }

        for (int tc = 0; tc < 4; tc++) {
            wins = new int[6];
            draws = new int[6];
            losses = new int[6];

            boolean isPossible = true;
            int totalMatches = 0;

            // 국가별 승무패 기록 저장
            st = new StringTokenizer(br.readLine());
            for (int team = 0; team < 6; team++) {
                wins[team] = parseInt(st.nextToken());
                draws[team] = parseInt(st.nextToken());
                losses[team] = parseInt(st.nextToken());
                totalMatches += wins[team] + draws[team] + losses[team];
                if (wins[team] + draws[team] + losses[team] != 5) {
                    isPossible = false;
                }

            }

            if (totalMatches != 30) {
                isPossible = false;
            }

            if (isPossible && backTracking(0)) {
                bw.append("1 ");
            } else {
                bw.append("0 ");
            }
        }
        bw.flush();
        bw.close();
    }
    
    public static boolean backTracking(int round) {
        if (round == 15) {
            return true;
        }
        
        int team1 = matches[round][0];
        int team2 = matches[round][1];

        // 경기 진행 승무패 결정
        //승 / 패
        if (wins[team1] > 0 && losses[team2] > 0) {
            wins[team1]--;
            losses[team2]--;
            if (backTracking(round + 1))
                return true;
            wins[team1]++;
            losses[team2]++;
        }
        
        // 무 무
        if (draws[team1] > 0 && draws[team2] > 0) {
            draws[team1]--;
            draws[team2]--;
            if (backTracking(round + 1)) return true;
            draws[team1]++;
            draws[team2]++;
        }

        // 패 / 승
        if (losses[team1] > 0 && wins[team2] > 0) {
            losses[team1]--;
            wins[team2]--;
            if (backTracking(round + 1))
                return true;
            losses[team1]++;
            wins[team2]++;
        }
        
        return false;
        
    }    
}
