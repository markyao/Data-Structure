import java.util.ArrayList;

/**
 * 图深度优先遍历
 *
 * @author yaotailin
 */
public class CC {

    private Graph graph;
    private boolean[] visited;
    private int ccCount = 0;

    public CC(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.V()];
        for (int v = 0; v < graph.V(); v++) {
            if (!visited[v]) {
                dfs(v);
                ccCount++;
            }
        }
    }

    private void dfs(int v) {
        visited[v] = true;
        for (Integer w : graph.adj(v)) {
            if (!visited[w]) {
                dfs(w);
            }
        }
    }

    public int count() {
        return ccCount;
    }


    public static void main(String[] args) {
        Graph graph = new Graph("D:\\\\Lab\\\\Data-Structure\\\\Graph\\\\g.txt");
        CC cc = new CC(graph);
        System.out.println(cc.count());
    }
}
