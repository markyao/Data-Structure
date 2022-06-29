import java.util.*;

/**
 * 图广度优先
 *
 * @author yaotailin
 */
public class SingleSourcePathBFS {

    private final Graph G;
    private final boolean[] visited;
    private final int[] pre;
    private final int s;

    public SingleSourcePathBFS(Graph G, int s) {
        this.G = G;
        this.s = s;
        visited = new boolean[G.V()];
        pre = new int[(G.V())];
        Arrays.fill(pre, -1);

        bfs(s);
    }

    private void bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        pre[s] = s;
        visited[s] = true;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (Integer w : G.adj(v)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v;
                }
            }
        }
    }

    public boolean isConnectedTo(int t) {
        G.validateVertex(t);
        return visited[t];
    }

    public Iterable<Integer> path(int t) {
        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnectedTo(t)) {
            return res;
        }
        int cur = t;
        while (cur != s) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("D:\\\\Lab\\\\Data-Structure\\\\Graph\\\\g.txt");
        SingleSourcePathBFS sspath = new SingleSourcePathBFS(graph, 0);
        System.out.println("0 -> 6 " + sspath.path(6));

    }
}
