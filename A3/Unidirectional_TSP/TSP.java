import java.util.*;
import java.io.*;

public class TSP{


    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new FileReader("test.txt"));

        int[][] graph;
        int[][] dp;
        int[][] next;
        int rows;
        int cols;
        while(scanner.hasNext()){
            rows = scanner.nextInt();
            cols = scanner.nextInt();
            graph = new int[rows][cols];
            dp = new int[rows][cols];
            next = new int[rows][cols];
            int ans = Integer.MAX_VALUE;
            int first = 0;

            // read and set the graph matrix

            // 3 4 1 2 8 6
            // 6 1 8 2 7 4
            // 5 9 3 9 9 5
            // 8 4 1 3 2 6
            // 3 7 2 8 6 4
            for(int i=0;i<rows;i++){
                for(int j=0; j<cols;j++){
                    graph[i][j] = scanner.nextInt();
                }
            }

            // inital all col cost val 
            for(int i=0;i<rows;i++){
                dp[i][cols-1] = graph[i][cols-1];
            }

            for(int j=cols-1; j>=0;j--){
                for(int i=0; i<rows; i++){
                    if(j==cols-1)
                        dp[i][j] = graph[i][j];
                    else{
                        int[] moves = {i,i-1,i+1};
                        if(i==0)
                            moves[1] = rows-1;
                        if(i== rows-1)
                            moves[2] = 0;
                        Arrays.sort(moves);
                        dp[i][j] = Integer.MAX_VALUE;
                        // check top diagonal, right, and bottom diagonal 
                        for(int k=0;k <3;k++){
                            int val = dp[moves[k]][j+1] + graph[i][j];
                            if(val < dp[i][j]){
                                dp[i][j] = val;
                                next[i][j] = moves[k];
                            }
                                
                        }
                    }
                }
            }
            for(int i=0; i<rows;i++){
                if(dp[i][0] < ans){
                    ans = dp[i][0];
                    first = i;
                }
            }
            System.out.print(first+1);
            for(int i=next[first][0],j=1; j<cols; i=next[i][j],j++){
                if(j!=cols){
                    System.out.print(" ");
                }
                System.out.print(i+1);
            }
            System.out.println("\n" + ans);
        }
        scanner.close();

    }
}