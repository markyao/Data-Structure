import java.util.ArrayList;
import java.util.Collections;

/**
 * 图深度优先遍历 单源路径
 *
 * @author yaotailin
 */
public class SingleSourcePath {

    private Graph graph;
    private int source;
    private int[] pre;


    public SingleSourcePath(Graph graph, int source) {
        graph.validateVertex(source);
        this.graph = graph;
        this.source = source;
        pre = new int[graph.V()];
        for (int i = 0; i < pre.length; i++) {
            pre[i] = -1;
        }
        dfs(source, source);
    }

    private void dfs(int v, int parent) {
        pre[v] = parent;
        for (Integer w : graph.adj(v)) {
            if (pre[w] == -1) {
                dfs(w, v);
            }
        }
    }

    public boolean isConnected(int t) {
        graph.validateVertex(t);
        return pre[t] != -1;
    }

    public Iterable<Integer> path(int t) {
        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnected(t)) {
            return res;
        }
        int cur = t;
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
        SingleSourcePath graphDFS = new SingleSourcePath(graph, 0);
        System.out.println("0->6 : " + graphDFS.path(6));
        System.out.println("0->4 : " + graphDFS.path(4));
    }
}
