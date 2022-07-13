
/**
 * 岛屿面积
 */
public class LeetCode200 {


    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    res += 1;
                    floodFill(i, j, grid);
                }
            }
        }
        return res;
    }

    private void floodFill(int x, int y, char[][] grid) {

        grid[x][y] = '0';
        if (x > 0 && grid[x - 1][y] == '1') {
            floodFill(x - 1, y, grid);
        }
        if (y > 0 && grid[x][y - 1] == '1') {
            floodFill(x, y - 1, grid);
        }
        if (x < grid.length - 1 && grid[x + 1][y] == '1') {
            floodFill(x + 1, y, grid);
        }
        if (y < grid[x].length - 1 && grid[x][y + 1] == '1') {
            floodFill(x, y + 1, grid);
        }
    }


    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };

        LeetCode200 leetCode200 = new LeetCode200();
        System.out.println(leetCode200.numIslands(grid));
    }
}
