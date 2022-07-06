import java.util.ArrayList;
import java.util.Stack;

/**
 * 图深度优先遍历
 *
 * @author yaotailin
 */
public class GraphDFSNR {

    private Graph graph;
    private boolean[] visited;
    private ArrayList<Integer> pre;

    public GraphDFSNR(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.V()];
        pre = new ArrayList<>(graph.V());
        for (int v = 0; v < graph.V(); v++) {
            if (!visited[v]) {
                dfs(v);
            }
        }
    }

    private void dfs(int s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(s);
        visited[s] = true;
        while (!stack.isEmpty()) {
            int v = stack.pop();
            pre.add(v);
            for (int w : graph.adj(v))
                if (!visited[w]) {
                    stack.push(w);
                    visited[w] = true;
                }
        }
    }

    public Iterable<Integer> getPreOrder() {
        return pre;
    }


    public static void main(String[] args) {
        Graph graph = new Graph("D:\\\\Lab\\\\Data-Structure\\\\Graph\\\\g.txt");
        GraphDFSNR graphDFS = new GraphDFSNR(graph);
        System.out.println(graphDFS.getPreOrder());
        //[0, 2, 6, 3, 1, 4, 5]
        //[0, 1, 3, 2, 6, 4, 5]
        //[6, 2, 3, 4, 1, 0, 5]
    }
}
