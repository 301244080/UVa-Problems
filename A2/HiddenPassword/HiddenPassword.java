import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * PWD
 */
public class HiddenPassword {

    public HiddenPassword(String inputStr){
        String[] orgStrs = inputStr.split(" ");
        int strLen = Integer.parseInt(orgStrs[0]);
        // System.out.println(strLen);
        int newLen = strLen*2;
        String str = orgStrs[1];
        String newStr = new String(str + str);
        // System.out.println(newStr + " " + newStr.length());
        String[] suffixArray = new String[strLen];
        String[] sortedSuffix = new String[strLen];
        for(int i=0;i<strLen;i++){
            suffixArray[i] = newStr.substring(i,newLen);
            sortedSuffix[i]  = newStr.substring(i,newLen);
        }
        
        Arrays.sort(sortedSuffix);

        for(int j=0;j<strLen;j++){
            if(sortedSuffix[j].substring(0,strLen).equals(suffixArray[j].substring(0,strLen))){
                System.out.println(j);
                break;
            }
        }


    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int linesSize = Integer.parseInt(br.readLine());
        for(int i=0;i<linesSize;i++){
            String currStr = br.readLine();
            HiddenPassword hp = new HiddenPassword(currStr);
        }
    }
    
}