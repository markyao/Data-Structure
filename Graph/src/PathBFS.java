import java.util.*;

/**
 * 图广度优先(所有路径)
 *
 * @author yaotailin
 */
public class PathBFS {

    private final Graph G;
    private final boolean[] visited;
    private final int[] pre;
    private final int s, t;

    public PathBFS(Graph G, int s, int t) {
        G.validateVertex(s);
        G.validateVertex(t);
        this.G = G;
        this.s = s;
        this.t = t;
        visited = new boolean[G.V()];
        pre = new int[(G.V())];
        Arrays.fill(pre, -1);
        bfs();
        for (boolean e : visited)
            System.out.print(e + " ");
        System.out.println();
    }

    private void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        pre[s] = s;
        visited[s] = true;
        if (s == t) {
            return;
        }
        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (Integer w : G.adj(v)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v;
                    if (w == t) {
                        return;
                    }
                }
            }
        }
    }

    public boolean isConnected() {
        return visited[t];
    }

    public Iterable<Integer> path() {
        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnected()) {
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
        Graph g = new Graph("D:\\\\Lab\\\\Data-Structure\\\\Graph\\\\g.txt");
        PathBFS path = new PathBFS(g, 0, 6);
        System.out.println(Arrays.toString(path.pre));
        System.out.println("0 -> 6 : " + path.path());

        PathBFS path2 = new PathBFS(g, 0, 5);
        System.out.println("0 -> 5 : " + path2.path());

        PathBFS path3 = new PathBFS(g, 0, 1);
        System.out.println("0 -> 1 : " + path3.path());

    }
}
