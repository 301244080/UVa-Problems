import java.util.*;


public class PokerHand{
    private Map<Integer,List<Character>> whiteMap = new HashMap<>();
    private Map<Integer,List<Character>> blackMap = new HashMap<>();
    final String WHITE_WIN = new String("White wins.");
    final String BLACK_WIN = new String("Black wins.");
    final String TIE = new String("tie");
    // private int[] res = new res[5];

    public static void main(String[] args) {
        fileIO readFile = new fileIO();
        List<String> strs = readFile.readFile("test.txt");
        PokerHand pk = new PokerHand(strs.get(0));
    }
    public PokerHand(String orgStr){
        String[] subStrs = orgStr.split(" +");
        whiteMap = deposSubStr(subStrs, whiteMap,0, 5);
        blackMap = deposSubStr(subStrs, blackMap, 5, subStrs.length);
        int[] whiteRes = ranking(whiteMap);
        int[] blackRes = ranking(blackMap);
        int whitePos=0, blackPos=0;
        while(whitePos < whiteRes.length && blackPos < blackRes.length){
            if(whiteRes[whitePos] == blackRes[blackPos]){
                System.out.println(TIE);
                break;
            }
            else if(whiteRes[whitePos] > blackRes[blackPos]){
                System.out.println(WHITE_WIN);
                break;
            }
            else{
                System.out.println(BLACK_WIN);
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
            else if(str.charAt(0) == 'A') value = 1;
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
        int[] res = new int[5];
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
        int[] res = new int[2];
        //res 0 stand for is straight flush or not, res 1 for max value for straight
        res[0] = -1;
        if(isStraight(map)[0]!= -1 && isFlush(map)[0]!=-1){
            res[0] = 9;
            res[1] = isStraight(map)[1];
        }
        return res;
    }

    public int[] isFourOfKind(Map<Integer,List<Character>> map){
        int[] res = new int[3];
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
        int[] res = new int[3];
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
        int[] res = new int[2];
        // res record is flush and the max value of flush
        res[0] = -1;
        Set<Integer> set = map.keySet();
        if(set.size()==5){
            Set<List<Character>> flushSet = new HashSet<>();
            // int i = 0;
            int max = Integer.MIN_VALUE;
            for(int key:set){
                flushSet.add(map.get(key));
                if(flushSet.size()!=1) return res;
                max = Math.max(max,key);
            }
            res[0] =6;
            res[1] = max;

        }
        return res;
    }

    public int[] isStraight(Map<Integer,List<Character>> map){
        // res record isStraight and max value of Straight
        int[] res = new int[2];
        res[0] = -1;
        int[] cardArr = new int[26];
        for(int key:map.keySet()){
            cardArr[key-1] = 1; 
            cardArr[key+12] = 1;
        }
        for(int i=0;i<22;i++){
            if(cardArr[i] == 1 && cardArr[i+1] == 1 && cardArr[i+2] == 1&& cardArr[i+3]==1 && cardArr[i+4]==1){
                res[0]=5;
                res[1] = cardArr[i+4];
            }
        }
        
        return res;
    }

    public int[] isThreeOfKind(Map<Integer,List<Character>> map){
        int[] res = new int[4];
        res[0] = -1;
        int single = 2;
        boolean findThree = false;
        for(int key:map.keySet()){
            if(map.get(key).size() == 3){
                res[0] = 4;
                res[1] = key;
                findThree = true;
            }
            else if(map.get(key).size()== 2 || map.get(key).size() == 4){
                break;
            }
            else if(findThree){
                res[single] = key;
                single++;
            }
        }
        return res;
    }

    public int[] isTwoPairs(Map<Integer,List<Character>> map){
        //size 3 record 2 paired number
        int[] res = new int[4];
        res[0] = -1;
        int times =0;
        for(int key:map.keySet()){
            if(times==2) {
                res[0] = 3;
            }
            if(map.get(key).size()==2){
                times++;
                res[times] = key;
            }
            if(map.get(key).size()==1) res[3] =key;
        }
        return res;
    }

    public int[] isPair(Map<Integer,List<Character>> map){
        int[] res = new int[2];
        res[0] = -1;
        for(int key:map.keySet()){
            if(map.get(key).size()==2){
                res[0] = 2;
                res[1] = key;
            }
        }
        return res;
    }

    public int[] isHighCard(Map<Integer,List<Character>> map){
        int[] res = new int[2];
        res[0] = 1;
        int max = Integer.MIN_VALUE;
        for(int key:map.keySet()){
            max = Math.max(max,key);
        }
        res[1] = max;
        return res;
    }

}