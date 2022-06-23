
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
                dfs(v, v);
            }
        }
    }

    private void dfs(int v, int parent) {
        visited[v] = true;
        for (Integer w : graph.adj(v)) {
            if (!visited[w]) {
                dfs(w, v);
            } else if (w != parent) {
                hasCycle = true;
            }
        }
    }

    public boolean hasCycle(){
        return hasCycle;
    }


    public static void main(String[] args) {
        Graph graph = new Graph("D:\\\\Lab\\\\Data-Structure\\\\Graph\\\\g.txt");
        CycleDetection graphDFS = new CycleDetection(graph);
        System.out.println(graphDFS.hasCycle());
    }
}
