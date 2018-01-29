import java.util.*;
import java.io.*;



public class Main{

    static final String letterTable = "ACGT";

    /*
    GATTACA
    ATTACA
    TTACA
    TACA
    ACA
    CA
    A

    After sort:

    A
    ACA
    ATTACA
    CA
    GATTACA
    TACA
    TTACA

    */

    // find largest common prefix
    public static String lcp(String str1, String str2){
        int lenSize = Math.min(str1.length(),str2.length());
        for (int i = 0; i < lenSize; i++) {
            if (str1.charAt(i) != str2.charAt(i))
                return str1.substring(0, i);
        }
        return str1.substring(0,lenSize);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
        int inputSize=Integer.parseInt(br.readLine());
        for(int i=0; i<inputSize; i++){
            String str = br.readLine();
            int strLen = str.length();
            String[] suffixStr = new String[strLen];
            String ans = "";
            int times = 1;

            for(int j=0;j<strLen;j++){
                suffixStr[j] = str.substring(j,strLen);
            }

            Arrays.sort(suffixStr);


            for(int k=0;k<strLen-1;k++){
                String currRepeat =  lcp(suffixStr[k],suffixStr[k+1]);

                if(currRepeat.length() > ans.length()){
                    ans = currRepeat;
                    times = 1;
                }
                if(currRepeat.equals(ans)){
                    times++;
                }
            }


            String res = ans.equals("") ? ("No repetitions found!") : (ans + " "+ times);
            System.out.println(res);
        }
    }

}