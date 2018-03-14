# Robots On Ice (1098)

## Data structure:     

    Queue, LinkedList, Point from awt

## Algorithm:    

    1. Inital 2-D Arrays graph for record matrix and 3 target points with their cost (s1...s3), and start searching the path from (0,0)

    2. Use breath first search keep search and valid the neighbour point, with global value count for calculate paths 

    3. there is 3 specific help method for the main bfs method solve(): isValid(), isDisconnect() and distance()

    4. isValid() for make sure next step's row & col number are still in the range by grid size. isDisconnect is by using queue to valid the point is visited or not visit yet. The distance method is implement Pythagorean theorem for compare and update the shortest path/point.

    5. keep update and return count value for each case