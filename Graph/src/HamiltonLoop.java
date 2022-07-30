import java.util.ArrayList;
import java.util.Collections;

public class HamiltonLoop {

    private Graph graph;
    private boolean[] visited;
    private int[] pre;
    private int end;

    public HamiltonLoop(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.V()];
        pre = new int[graph.V()];
        end = -1;
        dfs(0, 0);
    }

    private boolean dfs(int v, int parent) {
        visited[v] = true;
        pre[v] = parent;
        for (Integer w : graph.adj(v)) {
            if (!visited[w]) {
                if (dfs(w, v)) {
                    return true;
                }
            } else if (w == 0 && allVisited()) {
                end = v;
                return true;
            }
        }
        visited[v] = false;
        return false;
    }

    private boolean allVisited() {
        for (int v = 0; v < graph.V(); v++)
            if (!visited[v]) {
                return false;
            }
        return true;
    }


    public ArrayList<Integer> result() {
        ArrayList<Integer> res = new ArrayList<>();
        if (end == -1) {
            return res;
        }
        int cur = end;
        while (cur != 0) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(0);
        Collections.reverse(res);
        return res;
    }


    public static void main(String[] args) {
        String path = "D:/Lab/Data-Structure/Graph/hamilton/";
        Graph graph1 = new Graph(path + "g.txt");
        HamiltonLoop hamiltonLoop = new HamiltonLoop(graph1);
        System.out.println(hamiltonLoop.result());

        Graph graph2 = new Graph(path + "g2.txt");
        HamiltonLoop hamiltonLoop2 = new HamiltonLoop(graph2);
        System.out.println(hamiltonLoop2.result());
    }

}
