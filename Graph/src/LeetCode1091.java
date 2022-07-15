import java.util.LinkedList;
import java.util.Queue;

/**
 * 岛屿面积
 */
public class LeetCode1091 {

    public int shortestPathBinaryMatrix(int[][] grid) {
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        Queue<Integer> queue = new LinkedList<>();
        int dis = 1;

        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }
        if (n == 1) {
            return 1;
        }
        queue.add(0);
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int path = queue.remove();
                int x = path / n;
                int y = path % n;
                for (int d = 0; d < dirs.length; d++) {
                    int nx = x + dirs[d][0];
                    int ny = y + dirs[d][1];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < n && grid[nx][ny] == 0 && !visited[nx][ny]) {
                        if (nx == n - 1 && ny == n - 1) {
                            return dis + 1;
                        }
                        int idx = nx * n + ny;
                        queue.add(idx);
                        visited[nx][ny] = true;
                    }
                }
            }
            dis += 1;
        }
        return -1;
    }

    public int shortestPathBinaryMatrixV2(int[][] grid) {
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        int[][] dist = new int[n][n];
        Queue<Integer> queue = new LinkedList<>();

        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }
        if (n == 1) {
            return 1;
        }
        queue.add(0);
        visited[0][0] = true;
        dist[0][0] = 1;
        while (!queue.isEmpty()) {
            int path = queue.remove();
            int x = path / n;
            int y = path % n;
            for (int d = 0; d < dirs.length; d++) {
                int nx = x + dirs[d][0];
                int ny = y + dirs[d][1];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n && grid[nx][ny] == 0 && !visited[nx][ny]) {
                    int idx = nx * n + ny;
                    queue.add(idx);
                    visited[nx][ny] = true;
                    dist[nx][ny] = dist[x][y] + 1;
                    if (nx == n - 1 && ny == n - 1) {
                        return dist[nx][ny];
                    }
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[][] grid = {
                {0}
        };

        LeetCode1091 leetCode1091 = new LeetCode1091();
        System.out.println(leetCode1091.shortestPathBinaryMatrix(grid));
        System.out.println(leetCode1091.shortestPathBinaryMatrixV2(grid));

    }
}
