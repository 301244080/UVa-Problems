Check-the-check

Data structure: When reading the input, a 2-d integer array is used to store the board. After that, A self-defined data structure “Chess” is used to store the movement of each piece. It contains two integer arrays x[] and y[] that stores the move directions, x = -1 means moving up,  x = 1 means moving down, y = -1 means moving left, y = 1 means moving right. It also contains two integer type variable “steps” and “directions” which stores number of steps can a piece move at each direction and number of directions can a piece move (for example, a Queen piece has 8 directions and 8 steps). 


Algorithm: 
0. Initialize "Chess" data structures that stores movement pattern for each piece
1. read in a board as a 2-D array
2. loop through each piece in the board, determine which side it belongs to (white or black)
3. Find the corresponding "Chess" data structure, loop though x[] and y[] directions and steps under its corresponding “chess” data structure in order to enumerate all possible places it is able to reach, break the loop if this piece reaches the edge of any other pieces. 
4. The message (white or black side wins) will be displayed when the piece reaches the King on the other side 
5. If either side of King is not reached, the draw message will be displayed in the end. 