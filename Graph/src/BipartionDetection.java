
/**
 * 二分图检测
 *
 * @author yaotailin
 */
public class BipartionDetection {

    private Graph graph;
    private boolean[] visited;
    private int[] colors;
    private boolean bipartion = true;


    public BipartionDetection(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.V()];
        colors = new int[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            colors[i] = -1;
        }
        for (int v = 0; v < graph.V(); v++) {
            if (!visited[v]) {
                if (!dfs(v, 0)) {
                    bipartion = false;
                    break;
                }
            }
        }
    }

    private boolean dfs(int v, int color) {
        visited[v] = true;
        colors[v] = color;
        for (Integer w : graph.adj(v)) {
            if (!visited[w]) {
                if (!dfs(w, v)) {
                    return false;
                }
            } else if (colors[w] == colors[v]) {
                return false;
            }
        }
        return true;
    }

    public boolean hasBipartition() {
        return bipartion;
    }


    public static void main(String[] args) {
        Graph graph = new Graph("D:\\\\Lab\\\\Data-Structure\\\\Graph\\\\g.txt");
        BipartionDetection graphDFS = new BipartionDetection(graph);
        System.out.println(graphDFS.hasBipartition());
    }


}
