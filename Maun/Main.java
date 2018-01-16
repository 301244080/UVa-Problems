import java.util.*;
// Working program using BufferedReader
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.*;

 


class PokerHand{

    public Map<Integer,List<Character>> whiteMap = new TreeMap<>(Collections.reverseOrder());
    public Map<Integer,List<Character>> blackMap = new TreeMap<>(Collections.reverseOrder());
    final String BLACK_WIN  = new String("White wins.");
    final String WHITE_WIN = new String("Black wins.");
    final String TIE = new String("Tie.");
    public String result;

    public PokerHand(String orgStr){
        String[] subStrs = orgStr.split(" +");
        whiteMap = deposSubStr(subStrs, whiteMap,0, 5);
        blackMap = deposSubStr(subStrs, blackMap, 5, subStrs.length);
        int[] whiteRes = ranking(whiteMap);
        int[] blackRes = ranking(blackMap);
        int whitePos=0;
        int blackPos=0;
        // System.out.println(whiteRes[whitePos]);
        // System.out.println(blackRes[blackPos]);
        while(whitePos < whiteRes.length && blackPos < blackRes.length){
            if(whiteRes[whitePos] == blackRes[blackPos]){
                
                whitePos++;
                if(whitePos == whiteRes.length || blackPos == blackRes.length){
                    this.result = TIE;
                    break;
                } 
                blackPos++;
            }
            else if(whiteRes[whitePos] > blackRes[blackPos]){
                this.result = WHITE_WIN;
                break;
            }
            else{
                this.result = BLACK_WIN;
                break;
            }
            
        }
        
    }

    private Map<Integer,List<Character>>  deposSubStr(String[] strs, Map<Integer,List<Character>>  map, int start, int end){
        /* 
        

        */
        List<Character> suitList = new ArrayList<>();
        for(int i=start;i<end;i++){
            String str = strs[i];
            int strLen = str.length();
            int value;
            char suit = str.charAt(strLen-1);
            if(str.substring(0,2).equals("10")){
                
                value = 10;
            }
            else if(str.charAt(0) == 'K') value = 13;
            else if(str.charAt(0) == 'Q') value = 12;
            else if(str.charAt(0) == 'J') value = 11;
            else if(str.charAt(0) == 'T') value = 10;
            else if(str.charAt(0) == 'A') value = 14;
            else{
                value = Integer.parseInt(str.substring(0,1));
            }
            if(map.containsKey(value)){
                suitList = map.get(value);
                suitList.add(suit);
            }
            else{
                suitList = new ArrayList<>();
                suitList.add(suit);
            }
            map.put(value, suitList);
            
        }
        return map;
    }

    public int[] ranking(Map<Integer,List<Character>> map){
        int[] res = new int[6];
        if(isStraightFlush(map)[0]!=-1){
            res = isStraightFlush(map);
        }
        else if(isFourOfKind(map)[0]!=-1){
            res = isFourOfKind(map);
        }
        else if(isFullHouse(map)[0]!=-1){
            res = isFullHouse(map);
        }
        else if(isFlush(map)[0]!=-1){
            res = isFlush(map);
        }
        else if(isStraight(map)[0]!=-1){
            res = isStraight(map);
        }
        else if(isThreeOfKind(map)[0]!=-1){
            res = isThreeOfKind(map);
        }
        else if(isTwoPairs(map)[0]!=-1){
            res = isTwoPairs(map);
        }
        else if(isPair(map)[0] != -1){
            res = isPair(map);
        }
        else{
            res = isHighCard(map);
        }
        
        return res;
    }

    public int[] isStraightFlush(Map<Integer,List<Character>> map){
        int[] res = new int[6];
        //res 0 stand for is straight flush or not, res 1 for max value for straight
        res[0] = -1;
        if(isStraight(map)[0]!= -1 && isFlush(map)[0]!=-1){
            res[0] = 9;
            res[1] = isStraight(map)[1];
        }
        return res;
    }

    public int[] isFourOfKind(Map<Integer,List<Character>> map){
        int[] res = new int[6];
        // res[0] for check, res[1] for 4 card number, res[2] for last number
        res[0] = -1;
        for(Integer key:map.keySet()){
            if(map.get(key).size()>1 && map.get(key).size()<4){
                break;
            }
            else{
                if(map.get(key).size()==4){
                    res[0] = 8;
                    res[1] = key;
                }
                if(map.get(key).size() == 1){
                    res[2] = key;
                }
            }
            
        }
        return res;
    }

    public int[] isFullHouse(Map<Integer,List<Character>> map){
        // res[0] for check, res[1] for three card number, res[2] for pair card number
        int[] res = new int[6];
        res[0] = -1;
        boolean pairFlag = false;
        boolean threeFlag = false;
        if(map.keySet().size()==2){
            for(int key:map.keySet()){
                if(map.get(key).size() == 3){
                    threeFlag = true;
                    res[1] = key;
                }
                else if(map.get(key).size()==2){
                    pairFlag = true;
                    res[2] = key;
                }
            }
            if(pairFlag && threeFlag){
                res[0] = 7;
            }
        }        
        return res;
    }

    public int[] isFlush(Map<Integer,List<Character>> map){
        int[] res = new int[6];
        // res[0] record is flush,res[1:5] for sorted card value 
        res[0] = -1;
        int pos =1;
        Set<Integer> set = map.keySet();
        Set<List<Character>> flushSet = new HashSet<>();
        for(int key:set){
            flushSet.add(map.get(key));
            if(flushSet.size()>1) return res;
            res[pos] = key;
            pos++;
        }
        res[0] = 6;
        return res;
    }

    public int[] isStraight(Map<Integer,List<Character>> map){
        // res[0] record isStraight, res[1] end value of Straight
        int[] res = new int[2];
        res[0] = -1;
        int[] cardArr = new int[28];
        for(int key:map.keySet()){
            cardArr[key-1] = 1; 
            cardArr[key+12] = 1;
        }
        for(int i=0;i<22;i++){
            if(cardArr[i] == 1 && cardArr[i+1] == 1 && cardArr[i+2] == 1&& cardArr[i+3]==1 && cardArr[i+4]==1){
                res[0]=5;
                
                if(i+4>14){
                    res[1] = i+4-13;
                }
                else{
                    res[1] = i+4;
                }
            }
        }
        
        return res;
    }

    public int[] isThreeOfKind(Map<Integer,List<Character>> map){
        // res[0] for isThreeOfKind, res[1] for 3 card value, res[2] for max value, res[3] for 2nd max value
        int[] res = new int[4];
        res[0] = -1;
        int single = 2;
        boolean findThree = false;
        if(map.keySet().size()!=2) return res;
        for(int key:map.keySet()){
            if(map.get(key).size() == 3){
                res[0] = 4;
                res[1] = key;
                findThree = true;
            }
            else if(map.get(key).size()== 2 || map.get(key).size() == 4){
                break;
            }
            else if(map.get(key).size() == 1){
                res[single] = key;
                single++;
            }
        }
        return res;
    }

    public int[] isTwoPairs(Map<Integer,List<Character>> map){
        // res[0] for isTwoPairs, res[1] for max pair value, res[2] for second pair value, res[3] for left card value
        int[] res = new int[4];
        res[0] = -1;
        int times =0;
        for(int key:map.keySet()){
            if(map.get(key).size()==2){
                times++;
                res[times] = key;
            }
            if(times==2) {
                res[0] = 3;
            }
            if(map.get(key).size()==1) res[3] =key;
        }
        return res;
    }

    public int[] isPair(Map<Integer,List<Character>> map){
        // res[0] for isPair, res[1] for pair value, res[2:4] for three left card sorted value
        int[] res = new int[7];
        res[0] = -1;
        int pos = 2;
        for(int key:map.keySet()){
            if(map.get(key).size()==2){
                res[0] = 2;
                res[1] = key;
            }
            if(map.get(key).size()==1){
                res[pos] = key;
                pos++;
            }
        }
        return res;
    }

    public int[] isHighCard(Map<Integer,List<Character>> map){
        // res[0] for ishighCard, res[1:5] for sorted card value;
        int[] res = new int[6];
        res[0] = 1;
        int pos = 1;
        for(int key:map.keySet()){
            res[pos] = key;
            pos++;
        }  
        return res;
    }

}

class Main{

    public static void main(String[] args) {
        List<String> records = new ArrayList<String>();
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while ((line = reader.readLine()) != null)
            {
                records.add(line);
            }
        }
        catch (Exception e)
        {
          System.err.format("Exception occurred trying to read");
          e.printStackTrace();
        }
        for(int i=0; i<records.size();i++){
            PokerHand pk = new PokerHand(records.get(i));
            System.out.println(pk.result);
        }
        
    }
}