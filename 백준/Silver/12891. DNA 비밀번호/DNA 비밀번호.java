import java.io.*;
import java.util.*;

public class Main {

    static int S, P, answer;
    static int[] ACTG = new int[4];
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        
        String dList = br.readLine();
        
        st = new StringTokenizer(br.readLine());
        int[] minVal = new int[4];
        for (int i = 0; i < 4; i++) {
            minVal[i] = Integer.parseInt(st.nextToken());
        }
        
        // 슬라이딩 윈도우
        for (int i = 0; i < P; i++) {
            updateCount(dList.charAt(i), 1);
        }
        
        if (isValid(minVal)) {
            answer++;
        }
        
        // 문자열 따라 윈도우 슬라이딩
        for (int i = P; i < S; i++) {
        	//먼저 지나간 문자 감소 후 새로 추가
            updateCount(dList.charAt(i - P), -1);
            updateCount(dList.charAt(i), 1);
            
            if (isValid(minVal)) {
                answer++;
            }
        }
        
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();    
    }
    
    // ACTG DNA 개수 카운트
    private static void updateCount(char dna, int value) {
        switch (dna) {
            case 'A':
                ACTG[0] += value;
                break;
            case 'C':
                ACTG[1] += value;
                break;
            case 'G':
                ACTG[2] += value;
                break;
            case 'T':
                ACTG[3] += value;
                break;
        }
    }
    
    // 부분 문자열 문자 최소 개수 확인
    private static boolean isValid(int[] minVal) {
        for (int i = 0; i < 4; i++) {
            if (ACTG[i] < minVal[i]) {
                return false;
            }
        }
        return true;
    }
}
