import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 图广度优先
 *
 * @author yaotailin
 */
public class GraphBFS {

    private Graph G;
    private boolean[] visited;
    private List<Integer> order;

    public GraphBFS(Graph G) {
        this.G = G;
        visited = new boolean[G.V()];
        order = new ArrayList<>(G.V());

        for (int v = 0; v < G.V(); v++) {
            if (!visited[v]) {
                bfs(v);
            }
        }

    }

    private void bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            order.add(v);
            for (Integer w : G.adj(v)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                }
            }
        }
    }

    public Iterable<Integer> order() {
        return order;
    }


    public static void main(String[] args) {
        Graph graph = new Graph("D:\\\\Lab\\\\Data-Structure\\\\Graph\\\\g.txt");
        GraphBFS bfs = new GraphBFS(graph);
        System.out.println("BFS Order : " + bfs.order());

    }
}
