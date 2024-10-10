import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringBuilder sb = new StringBuilder(br.readLine()); // 초기 문자열 입력
        int index = sb.length(); // 커서 위치 (문자열의 끝)
        int orderCnt = Integer.parseInt(br.readLine()); // 명령의 수 입력

        for (int i = 0; i < orderCnt; i++) {
            String command = br.readLine();
            switch (command.charAt(0)) {
                case 'P': // 문자 추가
                    char c = command.charAt(2);
                    sb.insert(index, c);
                    index++;
                    break;
                case 'L':
                    if (index > 0) {
                        index--;
                    }
                    break;
                case 'D':
                    if (index < sb.length()) {
                        index++;
                    }
                    break;
                case 'B':
                    if (index > 0) {
                        sb.deleteCharAt(index - 1);
                        index--;
                    }
                    break;
            }
        }

        // 최종 문자열 출력
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
