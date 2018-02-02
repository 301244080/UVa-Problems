import java.io.BufferedReader;
import java.io.InputStream;
import java.io.*;
import java.util.*;




public class Waldorf {
    static int[] location = new int[2];
    static int[] res = new int[2];

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

    public static List<Integer> binarySearch(SuffixArray suffixArr, int length, char target){
        int left = 0, right = length-1;
        int mid;
        int start, end;
        List<Integer> res = new LinkedList<>();
        res.add(-2);

        while(left <= right){
            mid = (left + right)/2;
            char currChar = suffixArr.suffixArray[mid].str.charAt(0);
            if(currChar == target){
                res.remove(0);
                start = mid;
                end = mid;
                while(start>0 && suffixArr.suffixArray[start-1].str.charAt(0) == target){
                    start--;
                }
                while(end < length-1 && suffixArr.suffixArray[end+1].str.charAt(0) == target ){
                    end++;
                }

                for(int i=start;i<=end;i++){
                    res.add(suffixArr.suffixArray[i].index);
                    
                }
                Collections.sort(res);
                return res;
            }
            else if(currChar < target){
                left = mid +1;
            }
            else{
                right = mid-1;
            }
        }
        return res;
    }

    private static boolean bfs(SuffixArray[] suffixArrays, int rowPos, int rowSize, int colPos, int colSize, char[] wordArr, int wordPos){
        List<Integer> topCheckNums, bottomCheckNums, leftCheckNums, rightCheckNums;
        // System.out.println(rowPos + " col: " + colPos + " char: " + wordArr[wordPos-1]);
        if(wordPos < wordArr.length && wordPos >0){
            // System.out.println(rowPos + " col: " + colPos + " char: " + wordArr[wordPos-1]);
            // check top line
            if(rowPos-1 >= 0){
                topCheckNums = binarySearch(suffixArrays[rowPos-1],colSize,wordArr[wordPos]);
                for(int topCheckNum:topCheckNums){
                    // check top right, top left, top
                    if(topCheckNum == colPos || topCheckNum == colPos-1){ 
                        if(bfs(suffixArrays, rowPos-1, rowSize, topCheckNum, colSize, wordArr,wordPos+1)) return true;
                    }
                    else if(topCheckNum == colPos+1){
                        if(bfs(suffixArrays, rowPos, rowSize, topCheckNum, colSize, wordArr,wordPos-1)){
                            return true;
                        } 
                        else if(bfs(suffixArrays, rowPos-1, rowSize, topCheckNum, colSize, wordArr,wordPos+1)) return true;
                    }
                }

            }
            // check bottom line
            if(rowPos+1 < rowSize){
                bottomCheckNums = binarySearch(suffixArrays[rowPos+1], colSize, wordArr[wordPos]);
                for(int bottomCheckNum: bottomCheckNums){
                    if(bottomCheckNum == colPos-1||bottomCheckNum == colPos || bottomCheckNum == colPos+1 ){
                        if(bfs(suffixArrays, rowPos+1, rowSize, bottomCheckNum, colSize, wordArr, wordPos+1)) return true;
                    }
                }

            }
            // check left
            if(colPos-1 >= 0){
                leftCheckNums = binarySearch(suffixArrays[rowPos], colSize, wordArr[wordPos]);
                for(int leftCheckNum:leftCheckNums){
                    if(leftCheckNum == colPos-1){
                        if(bfs(suffixArrays,rowPos, rowSize, leftCheckNum,colSize,wordArr, wordPos+1)) return true;
                    }
                }
                
            }
            // check right
            if(colPos+1 < colSize){
                rightCheckNums = binarySearch(suffixArrays[rowPos],colSize,wordArr[wordPos]);
                for(int rightCheckNum:rightCheckNums){
                    if(rightCheckNum == colPos +1){
                        if(bfs(suffixArrays,rowPos,rowSize,rightCheckNum,colSize,wordArr,wordPos+1)) return true;
                    }
                }
            }
        }
        // else if (wordPos==0) {
            
        // }
        return false;
    }

    private static int[] search(String word, SuffixArray[] suffixArrays, int rowSize, int colSize){
        char[] wordArr = word.toCharArray();
        int wordPos=0, rowPos = 0;
        List<Integer> currRes = new LinkedList<>();
        // search word in word array
        // binary search for find start char
        while(rowPos< rowSize && rowPos >=0 && wordPos >=0 && wordPos<wordArr.length){
            res[0] = rowPos;
            currRes = binarySearch(suffixArrays[rowPos],colSize,wordArr[wordPos]);
            if( currRes.get(0)!= -2){
                for(int i=0; i<currRes.size();i++){
                    res[1] = currRes.get(i);
                    int colPos = res[1];
                    // bfs to find the word 
                    if(bfs(suffixArrays, rowPos, rowSize, colPos, colSize, wordArr, wordPos+1)) return res;
                }
            }
            rowPos++;
        }
        return res;

    }

    public static void main(String[] args) throws Exception {
        try {
            // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader br = new BufferedReader(new FileReader("test.txt"));
            int caseNum = Integer.parseInt(br.readLine());
            for(int i=0;i<caseNum;i++){

                // read space
                br.readLine();
                
                String[] gridSize = br.readLine().split(" ");
                int rowSize = Integer.parseInt(gridSize[0]), colSize = Integer.parseInt(gridSize[1]);
                // create suffixArray array for grid
                SuffixArray[] gridSuffixArrays = new SuffixArray[rowSize];
                for(int j=0; j < rowSize; j++){
                    gridSuffixArrays[j] = new SuffixArray(br.readLine().toLowerCase(),colSize);
                }

                // create suffixArray array for words ? or check each word
                int wordNum =Integer.parseInt(br.readLine());

                // search word 
                for(int k=0;k<wordNum;k++){
                    String currWord = br.readLine().toLowerCase();
                    location = search(currWord,gridSuffixArrays, rowSize, colSize);
                    // print result
                    location[0]++;
                    location[1]++;
                    System.out.println(location[0]+ " " + location[1]);
                }
                if(i<caseNum-1) System.out.println();
            }
            br.close();

        } catch (Exception e) {
            //TODO: handle exception
            System.err.format("Exception occurred trying to read");
            e.printStackTrace();
        }
        

    }

}