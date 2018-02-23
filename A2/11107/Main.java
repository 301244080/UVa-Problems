import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main{
    static int[] location = new int[2];

    static class SuffixArrayEle {
        int index;
        String str;
        SuffixArrayEle(int i, String s){
            index = i;
            str = s;
        }
    }

    static class SuffixArray{
        SuffixArrayEle[] suffixArray;
        SuffixArray(String s, int strLen){
            suffixArray = new SuffixArrayEle[strLen];
            for(int i=0; i <strLen;i++){
                suffixArray[i] = new SuffixArrayEle(i,s.substring(i,strLen));
            }
            Arrays.sort(suffixArray,(sA1,sA2)->sA1.str.compareTo(sA2.str));
        }
    }

// find largest common prefix
    public static String lcp(String str1, String str2){
        int lenSize = Math.min(str1.length(),str2.length());
        for (int i = 0; i < lenSize; i++) {
            if (str1.charAt(i) != str2.charAt(i))
                return str1.substring(0, i);
        }
        return str1.substring(0,lenSize);
    }
    

    public static void main(String[] args) {
        try{
            // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader br = new BufferedReader(new FileReader("test.txt"));
            int caseNum = Integer.parseInt(br.readLine());
            String ans = "";
            String currLine;
            int currLen;
            while(caseNum != 0){
                ans = "";
                Set<String> res = new TreeSet<>();
                SuffixArray[] suffixArrays = new SuffixArray[caseNum];
                for(int i=0; i< caseNum; i++){
                    currLine = br.readLine();
                    currLen  = currLine.length();
                    suffixArrays[i] = new SuffixArray(currLine, currLen);
                    // System.out.println(suffixArrays[i].suffixArray[1].str);
                }
                

                // search case and compare j, j+1 to find the longest same 
                int times = 0;
                for(int j=0; j < caseNum-1; j++){
                    int strLen = Math.min(suffixArrays[j].suffixArray[0].str.length(), suffixArrays[j+1].suffixArray[0].str.length());
                    for(int k=0; k<suffixArrays[j].suffixArray.length;k++){
                        for(int m=0; m<suffixArrays[j+1].suffixArray.length; m++){
                            String currLCP = lcp(suffixArrays[j].suffixArray[k].str,suffixArrays[j+1].suffixArray[m].str);
                            if(currLCP.length()> Math.floor(strLen/2)  && currLCP.length() >= ans.length() && !currLCP.equals("")){
                                ans = currLCP;
                                res.add(ans);
                                // System.out.println(ans);
                                times++;
                                // break;
                            }
                        }
                        
                    }
                }
                
                if(times == 0 ) {
                    res.add("?");
                }
                caseNum = Integer.parseInt(br.readLine());
                for(String s:res){
                    System.out.println(s);
                }
                if(caseNum!=0) System.out.println("");
            }
            // System.out.println(ans);
            


        } catch (Exception e) {
            //TODO: handle exception
            System.err.format("Exception occurred trying to read");
            e.printStackTrace();
        }
    }
}
