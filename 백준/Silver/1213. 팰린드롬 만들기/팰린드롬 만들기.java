import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String palindrome = br.readLine();
        boolean check = false;
        int oddCount = 0;
        int alphabet[] = new int[26];
        for (int i = 0; i < palindrome.length(); i++) {
            alphabet[palindrome.charAt(i) - 65]++;
        }

        String middle = "";
        StringBuilder half = new StringBuilder();


        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] % 2 != 0) {
                oddCount++;
                middle = String.valueOf((char) (i + 65));
            }
            for (int j = 0; j < alphabet[i] / 2; j++) {
                half.append((char) (i + 65));
            }
        }

        if (oddCount > 1) {
            bw.write("I'm Sorry Hansoo");
        } else{
            String palindHalf = half.toString();
            String result = palindHalf + middle + half.reverse().toString();

            bw.write(result);
        }
        bw.flush();
        bw.close();


    }
}
