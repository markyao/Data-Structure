public class LeetCode785 {

    private boolean[] visited;
    private int[] colors;
    private int[][] graph;

    public boolean isBipartite(int[][] graph) {
        this.graph = graph;
        int V = graph.length;
        visited = new boolean[V];
        colors = new int[V];

        for (int v = 0; v < V; v++) {
            if (!visited[v]) {
                if (!dfs(v, 0)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean dfs(int v, int color) {
        visited[v] = true;
        colors[v] = color;
        for (int w : graph[v]) {
            if (!visited[w]) {
                if (!dfs(w, 1 - color)) {
                    return false;
                }
            } else if (colors[w] == colors[v]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        double[][] class_score = {{10.0, 99, 99}, {100, 98, 97}, {100, 100, 99.5}, {99.5, 99, 98.5}};
        System.out.println("第二行第二列元素的值：" + class_score[1][1]);
        System.out.println("第四行第一列元素的值：" + class_score[3][0]);
    }
}
