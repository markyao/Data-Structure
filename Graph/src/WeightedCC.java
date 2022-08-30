import java.util.ArrayList;
import java.util.Arrays;

/**
 * 图深度优先遍历
 *
 * @author yaotailin
 */
public class WeightedCC {

    private WeightedGraph graph;
    private int[] visited;
    private int ccCount = 0;

    public WeightedCC(WeightedGraph graph) {
        this.graph = graph;
        visited = new int[graph.V()];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = -1;
        }
        for (int v = 0; v < graph.V(); v++) {
            if (visited[v] == -1) {
                dfs(v, ccCount);
                ccCount++;
            }
        }
    }

    private void dfs(int v, int ccid) {
        visited[v] = ccid;
        for (Integer w : graph.adj(v)) {
            if (visited[w] == -1) {
                dfs(w, ccid);
            }
        }
    }

    public int count() {
        System.out.println(Arrays.toString(visited));
        return ccCount;
    }

    /**
     * 两个顶点是否连接
     *
     * @param v
     * @param w
     * @return
     */
    public boolean isConnected(int v, int w) {
        graph.validateVertex(v);
        graph.validateVertex(w);
        return visited[v] == visited[w];
    }

    /**
     * 图中联通分量
     *
     * @return
     */
    public ArrayList<Integer>[] components() {
        ArrayList<Integer>[] res = new ArrayList[ccCount];
        for (int i = 0; i < res.length; i++) {
            res[i] = new ArrayList<>();
        }
        for (int v = 0; v < graph.V(); v++) {
            res[visited[v]].add(v);
        }
        return res;
    }
}
