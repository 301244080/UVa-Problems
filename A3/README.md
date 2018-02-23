# Unidirectional TSP(116)

## Data structure:     

    Arrays, 2-D Array

## Algorithm:    

    1. Inital 2-D Arrays graph for record matrix, dp for future dp process, and next for record each node previous node

    2. Since as the requirment, if there is more than one path of minimal weight the path that is lexicographically smallest should be output. If we do dynamic program from start to the end, we can't trace the path back.
    So I decide from the end to the start node.

    3. Build a dp function search three side moves' value. We need make sure the new row position will  be still in the range. Then sort moves by row number, and update dp table.

    4. Search the first col in dp for find the Min_value, the Min_val's row number is the first print row number. Use the next 2-Array traceback the path. 

    5. Print the Min_val