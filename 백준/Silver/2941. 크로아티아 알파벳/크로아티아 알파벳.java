import java.io.*;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] croatia = new String[]{"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
        String croatiaString = br.readLine();
        for(int i=0; i<croatia.length; i++){
            if(croatiaString.contains(croatia[i])){
                croatiaString = croatiaString.replace(croatia[i], "@");
            }
        }
        System.out.println(croatiaString.length());
    }
}