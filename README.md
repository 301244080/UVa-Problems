# PokerHands(10315)

## Data structure:     

    Specific HashMap named TreeMap, List from java.util

## Algorithm:    

    1. Split input String by space as 10 diffierent sub String. First five strings represent white player's cards and last five are for black player. 

    2. Split each card with card value and card type, put them in a HashMap. For the HashMap, key is the card value and following values are card type under same card value.

    3. Build private methods for valid diffrent dealt, each method will return an integer array that first value contains is valid or not, and following integers are ordred card value from player's cards. 

    4. Calculate player's highest card dealt and save two result arrays.

    5. From the start to the end of each result array, use a while loop to check until the script find which player have the higher result number in their result array. If all numbers are same, they're "Tie".

    6. Return the compared result.


# ErdosNumber(p10044)

## Data structure:     

    HashMap, Queue, Set, List from java.util

## Algorithm:    

    1. Create two user create class.         
    
	  a. First is ErdosInput, contains Integer number of senarios, Integer number of Authors, Integer number of Papers, personal define class Paper array Papers and String Array target authors.                 
	  
      b. Second is Paper, contains List of String authors and  String paper's name.

    2. Split and Parse input value to ErdosInput from test case.

    3. Create a HashMap from author's relationship, key is author and value is a Set(for avoid same value) contains all authors who has same paper with key author.

    4. Put the map in and private method named bfs for doing the breath first search, which can create a new map result that sort all author as key and their Erdos number as value. 

        For bfs method:

                    a. Create a new map for collect the result, and a Queue for doing value result. And initial ErdosValue 1 for next author's Erdos number.            

                    b. Push Erdos in the Queue and put (Erdos,0) in the Map.            
                    
                    c. While the queue is not empty, poll an author from queue and start searching all one-level relate authors with "just-poll author" from the relationship map.            
                    
                    d. If the result map does not contain related author yet, put it in the result map and set value as current ErdosValue and insert it in the Queue. Else jump to the next related author.            
                    
                    e. Increase the ErdosValue by 1 and back to step b.

    5. Return the author's Erdos number by get author name from result map.