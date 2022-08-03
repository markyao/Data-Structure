import java.util.ArrayList;
import java.util.Collections;

public class HamiltonLoop2 {

    private Graph graph;
    private int[] pre;
    private int end;

    public HamiltonLoop2(Graph graph) {
        this.graph = graph;
        pre = new int[graph.V()];
        end = -1;
        dfs(0, 0, 0, graph.V());
    }

    private boolean dfs(int v, int parent, int visited, int left) {
        visited += (1 << v);
        pre[v] = parent;
        left--;
        if (left == 0 && graph.hasEdge(v, 0)) {
            end = v;
            return true;
        }
        for (Integer w : graph.adj(v)) {
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
        HamiltonLoop2 hamiltonLoop = new HamiltonLoop2(graph1);
        System.out.println(hamiltonLoop.result());

        Graph graph2 = new Graph(path + "g2.txt");
        HamiltonLoop2 hamiltonLoop2 = new HamiltonLoop2(graph2);
        System.out.println(hamiltonLoop2.result());
    }

}
