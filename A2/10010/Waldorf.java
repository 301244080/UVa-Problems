import java.io.BufferedReader;
import java.io.InputStream;
import java.io.*;
import java.util.*;




public class Waldorf {

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

    public static int binarySearch(SuffixArray suffixArr, int length, char target){
        int left = 0, right = length-1;
        int mid;
        
        while(left < right){
            mid = (left + right)/2;
            char[] currArr = suffixArr.suffixArray[mid].str.toCharArray();
            if(currArr[0] == target){
                return suffixArr.suffixArray[mid].index;
            }
            else if(currArr[0] < target){
                left = mid +1;
            }
            else{
                right = mid-1;
            }
        }
        return -1;
    }

    private static boolean bfs(SuffixArray[] suffixArrays, int rowPos, int rowSize, int colPos, int colSize, char[] wordArr, int wordPos){
        System.out.println(wordArr[wordPos]);
        int topCheckNum = -1, bottomCheckNum = -1 , leftCheckNum = -1, rightCheckNum = -1;
        if(wordPos < wordArr.length){
            // check top line
            if(rowPos-1 >= 0){
                topCheckNum = binarySearch(suffixArrays[rowPos-1],colSize,wordArr[wordPos]);
                // check top right, top left, top 
                if(topCheckNum == colPos-1 || topCheckNum == colPos || topCheckNum == colPos+1){
                    if(bfs(suffixArrays, rowPos-1, rowSize, topCheckNum, colSize, wordArr,wordPos+1)) return true;
                }
            }
            // check bottom line
            else if(rowPos+1 < rowSize){
                bottomCheckNum = binarySearch(suffixArrays[rowPos+1], colSize, wordArr[wordPos]);
                if(bottomCheckNum == colPos-1 || bottomCheckNum == colPos || bottomCheckNum == colPos+1 ){
                    System.out.println("bottom");
                    if(bfs(suffixArrays, rowPos+1, rowSize, bottomCheckNum, colSize, wordArr, wordPos +1)) return true;
                }
            }
            // check left
            else if(colPos-1 >= 0){
                leftCheckNum = binarySearch(suffixArrays[rowPos], colSize, wordArr[wordPos]);
                if(leftCheckNum == colPos-1){
                    System.out.println("left");
                    if(bfs(suffixArrays,rowPos, rowSize, leftCheckNum,colSize,wordArr, wordPos+1)) return true;
                }
            }
            // check right
            else if(colPos+1 < colSize){
                rightCheckNum = binarySearch(suffixArrays[rowPos],colSize,wordArr[wordPos]);
                if(rightCheckNum == colPos +1){
                    System.out.println("right");
                    if(bfs(suffixArrays,rowPos,rowSize,rightCheckNum,colSize,wordArr,wordPos+1)) return true;
                }
            }
        }
        return false;
    }

    private static int[] search(String word, SuffixArray[] suffixArrays, int rowSize, int colSize){
        int[] res = new int[2];
        char[] wordArr = word.toCharArray();
        int wordPos=0, rowPos = 0;
        int currRes;
        // search word in word array
        // binary search for find start char
        while(rowPos< rowSize && rowPos >=0 && wordPos >=0 && wordPos<wordArr.length){
            res[0] = rowPos;
            currRes = binarySearch(suffixArrays[rowPos],colSize,wordArr[wordPos]);

            if( currRes!= -1){
                res[1] = currRes;
                int colPos = res[1];
                // bfs to find the word
                if(bfs(suffixArrays, rowPos, rowSize, colPos, colSize, wordArr, wordPos+1)) return res;
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

                int[] location = new int[2];

                // search word 
                for(int k=0;k<wordNum;k++){
                    String currWord = br.readLine().toLowerCase();
                    location = search(currWord,gridSuffixArrays, rowSize, colSize);
                    

                    // print result
                    // System.out.println(location[0] + " " + location[1]);

                }


            }


        } catch (Exception e) {
            //TODO: handle exception
            System.err.format("Exception occurred trying to read");
            e.printStackTrace();
        }
        

    }

}