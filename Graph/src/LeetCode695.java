import java.util.HashSet;

/**
 * 岛屿面积
 */
public class LeetCode695 {

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int R,C;
    private int[][] grid;
    private HashSet<Integer>[] G;
    private boolean[] visited;

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
        G = constructGraph();
        int res = 0;
        visited = new boolean[G.length];

        for (int v = 0; v < G.length; v++) {
            int x = v / C, y = v % C;
            if (grid[x][y] == 1 && !visited[v]) {
                res = Math.max(res, dfs(v));
            }
        }
        return res;
    }

    private int dfs(int v) {
        visited[v] = true;
        int res = 1;
        for (int w : G[v]) {
            if (!visited[w]) {
                res += dfs(w);
            }
        }
        return res;
    }


    private HashSet<Integer>[] constructGraph() {
        HashSet<Integer>[] g = new HashSet[R * C];
        for(int i = 0; i < g.length; i ++) {
            g[i] = new HashSet<>();
        }

        for (int v = 0; v < g.length; v++) {
            int x = v / C, y = v % C;
            if (grid[x][y] == 1) {
                for (int d = 0; d < 4; d++) {
                    int nextX = x + dirs[d][0];
                    int nextY = y + dirs[d][1];
                    if (inArea(nextX, nextY) && grid[nextX][nextY] == 1) {
                        int next = nextX * C + nextY;
                        g[v].add(next);
                        g[next].add(v);
                    }
                }
            }
        }
        return g;
    }

    /**
     * @param x
     * @param y
     * @return
     */
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

        LeetCode695 leetCode695 = new LeetCode695();
        System.out.println(leetCode695.maxAreaOfIsland(grid));
//        int[][] grid2 = {{0, 1}};
//        System.out.println((new LeetCode695()).maxAreaOfIsland(grid2));
    }
}
