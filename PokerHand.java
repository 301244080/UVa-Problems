/*
        A poker deck contains 52 cards - each card has a suit which is one of clubs, diamonds, hearts, or spades
        (denoted C, D, H, S in the input data). Each card also has a value which is one of 2, 3, 4, 5, 6, 7, 8, 9,
        10, jack, queen, king, ace (denoted 2, 3, 4, 5, 6, 7, 8, 9, T, J, Q, K, A). For scoring purposes, the suits
        are unordered while the values are ordered as given above, with 2 being the lowest and ace the highest
        value.
        A poker hand consists of 5 cards dealt from the deck. Poker hands are ranked by the following
        partial order from lowest to highest
        High Card. Hands which do not fit any higher category are ranked by the value of their highest card.
        If the highest cards have the same value, the hands are ranked by the next highest, and so on.
        Pair. 2 of the 5 cards in the hand have the same value. Hands which both contain a pair are ranked
        by the value of the cards forming the pair. If these values are the same, the hands are ranked by
        the values of the cards not forming the pair, in decreasing order.
        Two Pairs. The hand contains 2 different pairs. Hands which both contain 2 pairs are ranked by the
        value of their highest pair. Hands with the same highest pair are ranked by the value of their
        other pair. If these values are the same the hands are ranked by the value of the remaining card.
        Three of a Kind. Three of the cards in the hand have the same value. Hands which both contain
        three of a kind are ranked by the value of the 3 cards.
        Straight. Hand contains 5 cards with consecutive values. Hands which both contain a straight are
        ranked by their highest card.
        Flush. Hand contains 5 cards of the same suit. Hands which are both flushes are ranked using the
        rules for High Card.
        Full House. 3 cards of the same value, with the remaining 2 cards forming a pair. Ranked by the
        value of the 3 cards.
        Four of a kind. 4 cards with the same value. Ranked by the value of the 4 cards.
        Straight flush. 5 cards of the same suit with consecutive values. Ranked by the highest card in the
        hand.
        Your job is to compare several pairs of poker hands and to indicate which, if either, has a higher
        rank.
        Input
        The input file contains several lines, each containing the designation of 10 cards: the first 5 cards are
        the hand for the player named �Black� and the next 5 cards are the hand for the player named �White�.
        Output
        For each line of input, print a line containing one of:
        Black wins.
        White wins.
        Tie.
        Sample Input
        2H 3D 5S 9C KD 2C 3H 4S 8C AH
        2H 4S 4C 2D 4H 2S 8S AS QS 3S
        2H 3D 5S 9C KD 2C 3H 4S 8C KH
        2H 3D 5S 9C KD 2D 3H 5C 9S KH
        Sample Output
        White wins.
        Black wins.
        Black wins.
        Tie.
*/

// package fileIO;

import java.util.*;
// import fileIO.fileIO;

public class PokerHand{
    private Map<Integer,List<Character>> whiteMap = new HashMap<>();
    private Map<Integer,List<Character>> blackMap = new HashMap<>();

    public static void main(String[] args) {
        fileIO readFile = new fileIO();
        List<String> strs = readFile.readFile("test.txt");
        // System.out.println(strs.get(0));
        PokerHand pk = new PokerHand(strs.get(0));
    }
    public PokerHand(String orgStr){
        String[] subStrs = orgStr.split(" +");
        whiteMap = deposSubStr(subStrs, whiteMap,0, 5);
        // isStraightFlush(whiteMap);
        // System.out.println(whiteMap.keySet());
        System.out.println(isPair(whiteMap)[1]);
        // blackMap = deposSubStr(subStrs, blackMap, 5, subStrs.length);
    }

    private Map<Integer,List<Character>>  deposSubStr(String[] strs, Map<Integer,List<Character>>  map, int start, int end){
        List<Character> suitList = new ArrayList<>();
        for(int i=start;i<end;i++){
            String str = strs[i];
            int strLen = str.length();
            int value;
            // System.out.println(str.substring(0,strLen-1));
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
            // 
            System.out.println(map.get(value));
            
        }
        return map;
    }

    public String ranking(Map<Integer,List<Character>> map){
        String whiteWin = new String("White wins.");
        String blackWin = new String("Black wins.");
        return new String("tie");
    }

    public int isStraightFlush(Map<Integer,List<Character>> map){
        
        if(map.containsKey(1) && map.containsKey(11) && map.containsKey(12) && map.containsKey(11) && map.containsKey(10)){
            List<Character> suitsA = map.get(1);
            if(map.get(11).containsAll(suitsA) && map.get(12).containsAll(suitsA) && map.get(13).containsAll(suitsA) && map.get(10).containsAll(suitsA)){
                return 9;
            }
            
        } 
        return -1;
    }

    public int isFourOfKind(Map<Integer,List<Character>> map){
        for(Integer key:map.keySet()){
            if(map.get(key).size()==4) return 8;
        }
        return -1;
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

    public int isStraight(Map<Integer,List<Character>> map){
        return 5;
    }

    public int isThreeOfKind(Map<Integer,List<Character>> map){
        for(int key:map.keySet()){

        }
        return 4;
    }

    public int[] isTwoPairs(Map<Integer,List<Character>> map){
        //size 3 record 2 paired number
        int[] res = new int[3];
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
        }
        return res;
    }

    public int[] isPair(Map<Integer,List<Character>> map){
        int[] res = new int[2];
        res[0] = -1;
        for(int key:map.keySet()){
            // System.out.println(map.get(key));
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