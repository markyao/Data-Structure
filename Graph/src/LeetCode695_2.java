
/**
 * 岛屿面积
 */
public class LeetCode695_2 {

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int R, C;
    private int[][] grid;
    private boolean[][] visited;

    public int maxAreaOfIsland(int[][] grid) {
        this.grid = grid;
        if (grid == null) {
            return 0;
        }
        R = grid.length;
        if (R == 0) {
            return 0;
        }
        C = grid[0].length;
        if (C == 0) {
            return 0;
        }
        int res = 0;
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    res = Math.max(res, dfs(i, j));
                }
            }
        }
        return res;
    }

    private int dfs(int x, int y) {
        visited[x][y] = true;
        int res = 1;
        for (int d = 0; d < dirs.length; d++) {
            int nextX = x + dirs[d][0];
            int nextY = y + dirs[d][1];
            if (inArea(nextX, nextY) && grid[nextX][nextY] == 1 && !visited[nextX][nextY]) {
                res += dfs(nextX, nextY);
            }
        }

        return res;
    }
    /*假設MAX_X與MAX_Y是圖片的寬跟高*/
//    void flood_fill(int x, int y, int color)
//    {
//        area[x][y] = color;
//        if(x > 0 && area[x-1][y] == 0) flood_fill(x - 1, y, color);
//        if(y > 0 && area[x][y-1] == 0) flood_fill(x, y - 1, color);
//        if(x < MAX_X && area[x+1][y] == 0) flood_fill(x + 1, y, color);
//        if(y < MAX_Y && area[x][y+1] == 0) flood_fill(x, y + 1, color);
//    }
    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};

        LeetCode695_2 leetCode695 = new LeetCode695_2();
        System.out.println(leetCode695.maxAreaOfIsland(grid));
//        int[][] grid2 = {{0, 1}};
//        System.out.println((new LeetCode695()).maxAreaOfIsland(grid2));
    }
}
