import java.io.*;
import java.util.*;

public class Main{
    static String[] currLine;
    public static int clockSize;
    public static int maxNum;
    static int count;
    static int[] clockArr =  new int[13];
    static boolean[] dp = new boolean[13];
    static boolean flag;

    public static void dfs(int current){
        if(current == clockSize){
            if(clockArr[current] + clockArr[current-1] + clockArr[current-2] <= maxNum){
                count ++;
            }
        }

        for(int i=2; i<=clockSize;i++){
            if(!dp[i]){
                if(i+clockArr[current-1]+clockArr[current-2]<=maxNum){
                    dp[i] = true;
                    clockArr[current] = i;
                    dfs(current+1);
                    dp[i] = false;
                }
            }
        }
    }


    public static void main(String[] args){
        try {
            // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader br = new BufferedReader(new FileReader("test.txt"));
            int caseSize = Integer.parseInt(br.readLine());
            while(caseSize!=0){
                caseSize --;
                count = 0;
                currLine = br.readLine().split(" ");
                clockSize = Integer.parseInt(currLine[0]);
                maxNum = Integer.parseInt(currLine[1]);  
                System.out.println("Permutation size: "+  clockSize);
                System.out.println("Maximum triplet sum: " + maxNum);
                clockArr[1] = 1;
                dp[1] = true;
                for(int i=2;i<= clockSize; i++){
                    for(int j=i+1; j<=clockSize;j++){
                        if(i+j+1<=maxNum){
                            flag = true;
                            clockArr[clockSize] = i;
                            clockArr[2] = j;
                            dp[i] = true;
                            dp[j] = true;

                            dfs(3);
                            dp[i] = false;
                            dp[j] = false;
                        }
                    }
                }
                System.out.println("Valid permutation: " + count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}