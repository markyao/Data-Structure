import java.util.ArrayList;
import java.util.Collections;

public class HamiltonPath2 {

    private Graph graph;
    private boolean[] visited;
    private int[] pre;
    private int end;
    private int s;

    public HamiltonPath2(Graph graph, int s) {
        this.graph = graph;
        this.s = s;
        visited = new boolean[graph.V()];
        pre = new int[graph.V()];
        end = -1;
        int visited = 0;
        dfs(s, s, visited, graph.V());
    }

    private boolean dfs(int v, int parent, int visited, int left) {
        visited += (1 << v);
        pre[v] = parent;
        left--;
        if (left == 0) {
            end = v;
            return true;
        }
        for (int w : graph.adj(v)) {
            if ((visited & (1 << w)) == 0) {
                if (dfs(w, v, visited, left)) {
                    return true;
                }
            }
        }
        visited -= (1 << v);
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
        HamiltonPath2 hamiltonPath = new HamiltonPath2(graph, 3);
        System.out.println(hamiltonPath.result());
    }

}
