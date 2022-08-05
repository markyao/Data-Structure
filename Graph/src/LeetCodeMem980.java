import java.util.Arrays;

/**
 * 哈密顿路径相关问题(记忆化搜索)
 * <p>
 * 不同路径 III
 */
public class LeetCodeMem980 {

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int[][] grid;
    private int R, C;
    private int start, end;
    private int[][] memo;

    public int uniquePathsIII(int[][] grid) {
        this.grid = grid;
        R = grid.length;
        C = grid[0].length;
        int left = R * C;
        memo = new int[1 << (R * C)][R * C];

        for (int i = 0; i < memo.length; i++) {
            Arrays.fill(memo[i], -1);
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    start = i * C + j;
                    grid[i][j] = 0;
                } else if (grid[i][j] == 2) {
                    end = i * C + j;
                    grid[i][j] = 0;
                } else if (grid[i][j] == -1) {
                    left--;
                }
            }
        }
        int visited = 0;
        return dfs(visited, start, left);
    }

    private int dfs(int visited, int v, int left) {
        if (memo[visited][v] != -1)
            return memo[visited][v];

        visited += (1 << v);
        left--;
        if (v == end && left == 0) {
            visited -= (1 << v);
            memo[visited][v] = 1;
            return 1;
        }

        int x = v / C, y = v % C;
        int res = 0;
        for (int d = 0; d < 4; d++) {
            int nextx = x + dirs[d][0], nexty = y + dirs[d][1];
            int next = nextx * C + nexty;
            if (inArea(nextx, nexty) && grid[nextx][nexty] == 0 && (visited & (1 << next)) == 0)
                res += dfs(visited, next, left);
        }

        visited -= (1 << v);
        memo[visited][v] = res;
        return res;
    }
    // [[1,0,0,0],
    // [0,0,0,0],
    // [0,0,2,-1]]

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    public static void main(String[] args) {
        LeetCodeMem980 leetCode980 = new LeetCodeMem980();
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
