import java.util.ArrayList;
import java.util.Collections;

/**
 * 图的所有路径（深度优先）
 *
 * @author yaotailin
 */
public class Path {

    private Graph graph;
    private int source;
    private int target;
    private boolean[] visited;
    private int[] pre;


    public Path(Graph graph, int source, int target) {
        graph.validateVertex(source);
        this.graph = graph;
        this.source = source;
        this.target = target;
        visited = new boolean[graph.V()];
        pre = new int[graph.V()];
        for (int i = 0; i < pre.length; i++) {
            pre[i] = -1;
        }
        dfs(source, source);
    }

    private boolean dfs(int v, int parent) {
        visited[v] = true;
        pre[v] = parent;
        if (v == target) {
            return true;
        }
        for (Integer w : graph.adj(v)) {
            if (!visited[w]) {
                if (dfs(w, v)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isConnected() {
        return visited[target];
    }

    public Iterable<Integer> path() {
        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnected()) {
            return res;
        }
        int cur = target;
        while (cur != source) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(source);
        Collections.reverse(res);
        return res;
    }


    public static void main(String[] args) {
        Graph graph = new Graph("D:\\\\Lab\\\\Data-Structure\\\\Graph\\\\g.txt");
        Path path = new Path(graph, 0, 5);
        System.out.println("0->5 : " + path.path());
    }
}
