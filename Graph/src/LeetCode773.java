import java.util.*;

/**
 * 滑动谜题
 */
public class LeetCode773 {

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public int slidingPuzzle(int[][] board) {
        Queue<String> queue = new LinkedList<>();
        HashMap<String, Integer> visited = new HashMap<>();

        String initialSate = getInitialSate(board);
        if ("123450".equals(initialSate)) {
            return 0;
        }
        queue.add(initialSate);
        visited.put(initialSate, 0);
        while (!queue.isEmpty()) {
            String cur = queue.remove();
            ArrayList<String> nexts = getNexts(cur);
            for (String next : nexts) {
                if (!visited.containsKey(next)) {
                    queue.add(next);
                    visited.put(next, visited.get(cur) + 1);
                    if ("123450".equals(next)) {
                        return visited.get(next);
                    }
                }
            }
        }
        return -1;

    }

    private ArrayList getNexts(String cur) {
        ArrayList<String> nexts = new ArrayList<>();
        int zdx = cur.indexOf('0');
        int zx = zdx / 3, zy = zdx % 3;
        char[] chars = cur.toCharArray();
        for (int d = 0; d < dirs.length; d++) {
            int nextX = zx + dirs[d][0], nextY = zy + dirs[d][1];
            if (inArea(nextX, nextY)) {
                String next = swap(chars, zdx, nextX * 3 + nextY);
                nexts.add(next);
            }
        }
        return nexts;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < 2 && y >= 0 && y < 3;
    }

    private String swap(char[] chars, int i, int j) {
        char ic = chars[i];
        char jc = chars[j];
        char t = chars[j];
        chars[j] = chars[i];
        chars[i] = t;
        String s = new String(chars);
        chars[i] = ic;
        chars[j] = jc;
        return s;
    }


    private String getInitialSate(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        //3 2 4
        //1 5 0
//        int[][] board = {{1, 2, 3}, {4, 0, 5}};
//        int[][] board = {{1,2,3},{5,4,0}};
//        int[][] board = {{4, 1, 2}, {5, 0, 3}};
        int[][] board = {{3, 2, 4}, {1, 5, 0}};

        LeetCode773 leetCode732 = new LeetCode773();
        System.out.println(leetCode732.slidingPuzzle(board));
    }
}
