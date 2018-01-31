import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;



import java.io.*;
import java.util.*;



public class sTest {
    static class Suffix {
        int index;
        String str;
        Suffix(int i, String s) {
            index = i;
            str = s;
        }
    }
    
    static class HiddenPassword{
        int resPos;
        HiddenPassword(String inputStr){
            String[] orgStrs = inputStr.split(" ");
            int strLen = Integer.parseInt(orgStrs[0]);
            String str = orgStrs[1];
            resPos = smallestRotationIndex(str, strLen);
        }
        int smallestRotationIndex(String s, int n) {
            s += s;
            Suffix[] suff = new Suffix[n];
            for (int i = 0; i < n; i++) {
                suff[i] = new Suffix(i, s.substring(i, i + n));
            }
            Arrays.sort(suff, (a, b) -> a.str.compareTo(b.str));
            return suff[0].index;
        }
        
    }
    
    
    public static void main(String[] args) throws Exception {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder resStrBuilder = new StringBuilder();
            int linesSize = Integer.parseInt(reader.readLine());
            if (linesSize == 0) System.out.println("please input valid number of test cases!");
            for (int i = 0; i < linesSize; i++) {
                String currStr = reader.readLine();
                HiddenPassword hp = new HiddenPassword(currStr);
                if (hp.resPos != -1) {
                    resStrBuilder.append(hp.resPos).append("\n");
                }
            }
            System.out.println(resStrBuilder.toString().trim());
        } catch (Exception e) {
            System.err.format("Exception occurred trying to read");
            e.printStackTrace();
        }
    }
}
