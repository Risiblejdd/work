package PTA;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Grid {
    int x, y;
    int steps;

    Grid(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solve {
    boolean[][] inq = new boolean[10][10];
    int[][] maze = new int[10][10];
    private int[] dir_x = { 0, 0, 1, -1 };
    private int[] dir_y = { 1, -1, 0, 0 };

    private boolean isValid(int x, int y, int size) {
        if (x >= size - 1 || x < 1 || y >= size - 1 || y < 1)  return false;
        if (maze[x][y] == 1)  return false;
        return !inq[x][y];
    }

    int bfs(int size) {
        Queue<Grid> q = new LinkedList<>();
        q.offer(new Grid(1, 1));

        Grid now;
        while (!q.isEmpty())
        {
            now = q.poll();
            if (now.x == size - 2 && now.y == size - 2)  return now.steps;
            for (int i = 0; i < 4; i++)
            {
                int newX = now.x + dir_x[i];
                int newY = now.y + dir_y[i];
                if (isValid(newX, newY, size))
                {
                    Grid temp = new Grid(newX, newY);
                    temp.steps = now.steps + 1;
                    q.offer(temp);
                    inq[newX][newY] = true;
                }
            }
        }

        return -1;
    }
}

public class Before {
    public static void main(String[] args) {
        Scanner in = new Scanner(new InputStreamReader(System.in));

        int size = in.nextInt();
        Solve s = new Solve();

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                s.maze[i][j] = in.nextInt();
            }
        }
        in.close();

        int res = s.bfs(size);
        if (res == -1)
            System.out.println("No solution");
        else
            System.out.println(res);
    }
}
