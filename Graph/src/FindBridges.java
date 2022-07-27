import java.util.ArrayList;

public class FindBridges {

    private Graph G;
    private boolean[] visited;

    private int[] ord;
    private int[] low;
    private int cnt;

    private ArrayList<Edge> res;

    public FindBridges(Graph G) {
        this.G = G;
        visited = new boolean[G.V()];
        ord = new int[G.V()];
        low = new int[G.V()];
        res = new ArrayList<>();
        cnt = 0;

        for (int v = 0; v < G.V(); v++) {
            if (!visited[v]) {
                dfs(v, v);
            }
        }
    }

    private void dfs(int v, int parent) {
        visited[v] = true;
        ord[v] = cnt;
        low[v] = ord[v];
        cnt++;
        for (Integer w : G.adj(v)) {
            if (!visited[w]) {
                dfs(w, v);
                low[v] = Math.min(low[v], low[w]);
                if (low[w] > ord[v]) {
                    res.add(new Edge(v, w));
                }
            } else if (w != parent) {
                low[v] = Math.min(low[v], low[w]);
            }
        }
    }

    public ArrayList<Edge> result() {
        return res;
    }

    public static void main(String[] args) {
        String path = "D:/Lab/Data-Structure/Graph/bridge/";
//
//        Graph g = new Graph(path + "g.txt");
//        FindBridges fb = new FindBridges(g);
//        System.out.println("Bridges in g : " + fb.result());
//
//        Graph g2 = new Graph(path + "g2.txt");
//        FindBridges fb2 = new FindBridges(g2);
//        System.out.println("Bridges in g2 : " + fb2.result());
////
//        Graph g3 = new Graph(path + "g3.txt");
//        FindBridges fb3 = new FindBridges(g3);
//        System.out.println("Bridges in g3 : " + fb3.result());
//
//        Graph tree = new Graph(path + "tree.txt");
//        FindBridges fb_tree = new FindBridges(tree);
//        System.out.println("Bridges in tree : " + fb_tree.result());

        Graph tree2 = new Graph(path + "tree2.txt");
        FindBridges fb_tree2 = new FindBridges(tree2);
        System.out.println("Bridges in tree2 : " + fb_tree2.result());
    }
}
