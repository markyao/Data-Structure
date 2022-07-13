
/**
 * 岛屿的周长
 */
public class LeetCode463 {


    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    res += floodFill(i, j, grid);
                }
            }
        }
        return res;
    }

    private int floodFill(int x, int y, int[][] grid) {

        int p = 4;
        if (x > 0 && grid[x - 1][y] == 1) {
            --p;
        }
        if (y > 0 && grid[x][y - 1] == 1) {
            --p;
        }
        if (x < grid.length - 1 && grid[x + 1][y] == 1) {
            --p;
        }
        if (y < grid[x].length - 1 && grid[x][y + 1] == 1) {
            --p;
        }
        return p;
    }


    public static void main(String[] args) {
        int[][] grid = {
                {1,0}
        };

        LeetCode463 leetCode200 = new LeetCode463();
        System.out.println(leetCode200.islandPerimeter(grid));
    }
}
