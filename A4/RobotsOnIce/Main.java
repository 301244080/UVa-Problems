import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {

    static int r1, c1, s1;
    static int r2, c2, s2;
    static int r3, c3, s3;
    static int rowNum, colNum;
    static boolean[][] grid;
    static int count;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static boolean isValid(int x, int y) {
        return x >= 0 && x < rowNum && y >= 0 && y < colNum;
    }

    public static int distance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public static boolean isDisconnected() {
        Queue<Point> q = new LinkedList<Point>();
        boolean[][] visited = new boolean[rowNum][colNum];
        boolean found = false;
        for (int i = 0; i < grid.length && !found; i++){
            for (int j = 0; j < grid[i].length && !found; j++){
                if (!grid[i][j]) {
                    q.add(new Point(i, j));
                    visited[i][j] = true;
                    found = true;
                }
            }
        }

        while (!q.isEmpty()) {
            Point c = q.poll();
            for (int i = 0; i < dx.length; i++) {
                int x = c.x + dx[i], y = c.y + dy[i];
                if (!isValid(x, y) || visited[x][y] || grid[x][y]) continue;
                q.add(new Point(x, y));
                visited[x][y] = true;
            }
        }
        for (int i = 0; i < visited.length; i++){
            for (int j = 0; j < visited[i].length; j++){
                if (!visited[i][j] && !grid[i][j]) return true;
            }
        }
        return false;
    }

    public static void solve(int x, int y, int step) {
        if (x == 0 && y == 1) {
            if (step == rowNum * colNum) count++;
            return;
        }
        if (x == r1 && y == c1 && step != s1) return;
        if (x == r2 && y == c2 && step != s2) return;
        if (x == r3 && y == c3 && step != s3) return;
        if (step == s1 && (x != r1 || y != c1)) return;
        if (step == s2 && (x != r2 || y != c2)) return;
        if (step == s3 && (x != r3 || y != c3)) return;
        if (step < s1 && distance(x, y, r1, c1) > (s1 - step)) return;
        if (step < s2 && distance(x, y, r2, c2) > (s2 - step)) return;
        if (step < s3 && distance(x, y, r3, c3) > (s3 - step)) return;

        if (isDisconnected()) return;

        // check neighborhood condition
        int urgent = 0, index = -1;
        for (int k = 0; k < dx.length; k++) {
            int r = x + dx[k], c = y + dy[k];
            if (!isValid(r, c) || grid[r][c] || (r == 0 && c == 1)) continue;
            int neighbors = 0;
            for (int i = 0; i < dx.length; i++) {
                int nr = r + dx[i], nc = c + dy[i];
                if (!isValid(nr, nc) || grid[nr][nc]) continue;
                neighbors++;
            }
            if (neighbors == 0) return;
            if (neighbors == 1) {
                urgent++;
                index = k;
            }
        }

        // applying action on neighborhood case
        if (urgent > 1) return;
        else if (urgent == 1) {
            grid[x + dx[index]][y + dy[index]] = true;
            solve(x + dx[index], y + dy[index], step + 1);
            grid[x + dx[index]][y + dy[index]] = false;
            return;
        }

        for (int k = 0; k < dx.length; k++) {
            int r = x + dx[k], c = y + dy[k];
            if (!isValid(r, c) || grid[r][c]) continue;
            grid[r][c] = true;
            solve(r, c, step + 1);
            grid[r][c] = false;
        }
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int caseNum = 1;
            while (true) {
                String[] toks = br.readLine().trim().split("[ ]+");
                rowNum = Integer.parseInt(toks[0]);
                colNum = Integer.parseInt(toks[1]);
                if (rowNum == 0 && colNum == 0) break;
                toks = br.readLine().trim().split("[ ]+");
                r1 = Integer.parseInt(toks[0]);
                c1 = Integer.parseInt(toks[1]);
                r2 = Integer.parseInt(toks[2]);
                c2 = Integer.parseInt(toks[3]);
                r3 = Integer.parseInt(toks[4]);
                c3 = Integer.parseInt(toks[5]);

                count = 0;
                grid = new boolean[rowNum][colNum];
                s1 = rowNum * colNum / 4;
                s2 = rowNum * colNum / 2;
                s3 = 3 * rowNum * colNum / 4;
                grid[0][0] = true;
                solve(0, 0, 1);
                System.out.println("Case " + (caseNum++) + ": " + count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}