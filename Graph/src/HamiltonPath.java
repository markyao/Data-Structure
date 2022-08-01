import java.util.ArrayList;
import java.util.Collections;

public class HamiltonPath {

    private Graph graph;
    private boolean[] visited;
    private int[] pre;
    private int end;
    private int s;

    public HamiltonPath(Graph graph, int s) {
        this.graph = graph;
        this.s = s;
        visited = new boolean[graph.V()];
        pre = new int[graph.V()];
        end = -1;
        dfs(s, s, graph.V());
    }

    private boolean dfs(int v, int parent, int left) {
        visited[v] = true;
        pre[v] = parent;
        left--;
        if (left == 0) {
            end = v;
            return true;
        }
        for (int w : graph.adj(v)) {
            if (!visited[w]) {
                if (dfs(w, v, left)) {
                    return true;
                }
            }
        }
        visited[v] = false;
        return false;
    }


    public ArrayList<Integer> result() {
        ArrayList<Integer> res = new ArrayList<>();
        if (end == -1) {
            return res;
        }
        int cur = end;
        while (cur != s) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);
        Collections.reverse(res);
        return res;
    }


    public static void main(String[] args) {
        String path = "D:/Lab/Data-Structure/Graph/hamilton/";
        Graph graph = new Graph(path + "g3.txt");
        HamiltonPath hamiltonPath = new HamiltonPath(graph, 3);
        System.out.println(hamiltonPath.result());
    }

}
