import java.util.ArrayList;

/**
 * 图深度优先遍历
 *
 * @author yaotailin
 */
public class GraphDFS {

    private Graph graph;
    private boolean[] visited;
    private ArrayList<Integer> pre;
    private ArrayList<Integer> post;

    public GraphDFS(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.V()];
        pre = new ArrayList<>(graph.V());
        post = new ArrayList<>(graph.V());
        for (int v = 0; v < graph.V(); v++) {
            if (!visited[v]) {
                dfs(v);
            }
        }
    }

    private void dfs(int v) {
        visited[v] = true;
        pre.add(v);
        for (Integer w : graph.adj(v)) {
            if (!visited[w]) {
                dfs(w);
            }
        }
        post.add(v);
    }

    public Iterable<Integer> getPreOrder() {
        return pre;
    }

    public Iterable<Integer> getPostOrder() {
        return post;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("D:\\\\Lab\\\\Data-Structure\\\\Graph\\\\g.txt");
        GraphDFS graphDFS = new GraphDFS(graph);
        System.out.println(graphDFS.getPreOrder());
        System.out.println(graphDFS.getPostOrder());
    }
}
