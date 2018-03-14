import java.io.*;
import java.util.*;

public class Main{
    static String[] currLine;
    public static int clockSize;
    public static int maxNum;
    static int count;
    public static Boolean isValid(List<Integer> res, int currSize){
        if(currSize > 2){
            if((res.get(currSize-1)+res.get(currSize-2)+res.get(currSize-3)) > maxNum){
                return false;
            }
        }
        return true;
    }
    public static void permute(int[] nums) {
        
        List<Integer> permutation = new ArrayList<>();
        if(nums.length == 0){
            return;
        }
        permutation.add(1);
        collectPermutation(nums,1,permutation);
        return;
    }
    
    private static void collectPermutation(int[] nums, int start, List<Integer> permutation){
        if(permutation.size() == clockSize){
            if(isValid(permutation,start)){
                count ++;
                System.out.println(permutation);
            }
            return;
        }
        
        for (int i=1; i<=permutation.size(); i++){
            List<Integer> newPermutation = new ArrayList<>(permutation);
            newPermutation.add(i,nums[start]);
            if(isValid(newPermutation,start+1)){
                collectPermutation(nums, start+1, newPermutation);
            }
            else{
                return;
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
                int[] clockArr  = new int[clockSize+2];
                for (int i = 0; i < clockSize; i++) {
                    clockArr[i] = i+1;    
                }
                permute(clockArr);
                System.out.println(count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}