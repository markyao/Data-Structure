import java.util.ArrayList;
import java.util.Collections;

public class Kruskal {

    private WeightedGraph G;
    private ArrayList<WeightedEdge> mst;

    public Kruskal(WeightedGraph g) {
        G = g;
        this.mst = new ArrayList<>();
        WeightedCC cc = new WeightedCC(G);
        if (cc.count() > 1) {
            return;
        }
        ArrayList<WeightedEdge> edges = new ArrayList<>();
        for (int v = 0; v < G.V(); v++) {
            for (Integer w : G.adj(v)) {
                if (v < w) {
                    edges.add(new WeightedEdge(v, w, G.weight(v, w)));
                }
            }
        }
        Collections.sort(edges);
        UF uf = new UF(G.V());
        for (WeightedEdge edge : edges) {
            int v = edge.getV();
            int w = edge.getW();
            if (!uf.isConnected(v, w)) {
                mst.add(edge);
                uf.unionElements(v, w);
            }
        }
    }

    public ArrayList<WeightedEdge> result() {
        return mst;
    }

    public static void main(String[] args){
        String path = "D:/Lab/Data-Structure/Graph/kruskal/";
        WeightedGraph g = new WeightedGraph(path+"g.txt");
        Kruskal kruskal = new Kruskal(g);
        System.out.println(kruskal.result());
    }
}
