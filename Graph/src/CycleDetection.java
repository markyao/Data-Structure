
/**
 * 图深度优先遍历
 *
 * @author yaotailin
 */
public class CycleDetection {

    private Graph graph;
    private boolean[] visited;
    private boolean hasCycle = false;

    public CycleDetection(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.V()];
        for (int v = 0; v < graph.V(); v++) {
            if (!visited[v]) {
                if (dfs(v, v)) {
                    hasCycle=true;
                    break;
                }
            }
        }
    }

    /**
     * 从顶点V开始，判断图中是否有环
     *
     * @param v
     * @param parent
     * @return
     */
    private boolean dfs(int v, int parent) {
        visited[v] = true;
        for (Integer w : graph.adj(v)) {
            if (!visited[w]) {
                if (dfs(w, v)) {
                    return true;
                }
            } else if (w != parent) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCycle() {
        return hasCycle;
    }


    public static void main(String[] args) {
        Graph graph = new Graph("D:\\\\Lab\\\\Data-Structure\\\\Graph\\\\g.txt");
        CycleDetection graphDFS = new CycleDetection(graph);
        System.out.println(graphDFS.hasCycle());
    }
}
