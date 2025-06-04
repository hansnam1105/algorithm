import java.io.*;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        word = word.toLowerCase();
        int[] count = new int[26];
        for(int i=0; i<word.length(); i++){
            int num = word.charAt(i) - 'a';
            count[num]++;
        }

        int max = 0;
        char answer = '?';
        for(int i=0; i<count.length; i++){
            if(max < count[i]){
                max = count[i];
                answer = (char)(i+'A');
            } else if(max == count[i]){
                answer = '?';
            }
        }

        System.out.println(answer);

    }
}