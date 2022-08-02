/**
 * 哈密顿路径相关问题
 * <p>
 * 不同路径 III
 */
public class LeetCode980 {

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int[][] grid;
    private int R, C;
    private boolean[][] visited;
    private int start, end;

    public int uniquePathsIII(int[][] grid) {
        this.grid = grid;
        R = grid.length;
        C = grid[0].length;
        visited = new boolean[R][C];
        int left = R * C;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    start = i * C + j;
                } else if (grid[i][j] == 2) {
                    end = i * C + j;
                } else if (grid[i][j] == -1) {
                    left--;
                }
            }
        }
        return dfs(start, left);
    }

    private int dfs(int v, int left) {
        int x = v / C, y = v % C;
        visited[x][y] = true;
        left--;
        if (left == 0 && v == end) {
            visited[x][y] = false;
            return 1;
        }

        int res = 0;
        for (int d = 0; d < dirs.length; d++) {
            int nextx = x + dirs[d][0], nexty = y + dirs[d][1];
            if (inArea(nextx, nexty) && grid[nextx][nexty] != -1 && !visited[nextx][nexty]) {
                res += dfs(nextx * C + nexty, left);
            }
        }
        visited[x][y] = false;
        return res;
    }
    // [[1,0,0,0],
    // [0,0,0,0],
    // [0,0,2,-1]]

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    public static void main(String[] args) {
        LeetCode980 leetCode980 = new LeetCode980();
        int[][] grid1 = {
                {1, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 2, -1}
        };
        System.out.println(leetCode980.uniquePathsIII(grid1));
        int[][] grid2 = {
                {1, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 2}
        };
        System.out.println(leetCode980.uniquePathsIII(grid2));
        int[][] grid3 = {
                {0, 1},
                {2, 0}
        };
        System.out.println(leetCode980.uniquePathsIII(grid3));
    }

}
