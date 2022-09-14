import java.util.ArrayList;
import java.util.Collections;

public class Prim {

    private WeightedGraph G;
    private ArrayList<WeightedEdge> mst;

    public Prim(WeightedGraph g) {
        G = g;
        this.mst = new ArrayList<>();
        WeightedCC cc = new WeightedCC(G);
        if (cc.count() > 1) {
            return;
        }

        boolean[] visited = new boolean[G.V()];
        visited[0] = true;
        //Prim
        for (int i = 1; i < G.V(); i++) {

            WeightedEdge minWeightedEdge = new WeightedEdge(-1, -1, Integer.MAX_VALUE);
            for (int v = 0; v < G.V(); v++) {
                if (visited[v]) {
                    for (Integer w : G.adj(v)) {
                        if (!visited[w] && G.weight(v, w) < minWeightedEdge.getWeight()) {
                            minWeightedEdge = new WeightedEdge(v, w, G.weight(v, w));
                        }
                    }
                }
            }
            mst.add(minWeightedEdge);
            visited[minWeightedEdge.getV()] = true;
            visited[minWeightedEdge.getW()] = true;

        }
    }

    public ArrayList<WeightedEdge> result() {
        return mst;
    }

    public static void main(String[] args) {
        String path = "D:/Lab/Data-Structure/Graph/prim/";
        WeightedGraph g = new WeightedGraph(path + "g.txt");
        Prim prim = new Prim(g);
        System.out.println(prim.result());
    }
}
