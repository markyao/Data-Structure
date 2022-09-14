import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Prim2 {

    private WeightedGraph G;
    private ArrayList<WeightedEdge> mst;

    public Prim2(WeightedGraph g) {
        G = g;
        this.mst = new ArrayList<>();
        WeightedCC cc = new WeightedCC(G);
        if (cc.count() > 1) {
            return;
        }

        boolean[] visited = new boolean[G.V()];
        visited[0] = true;
        //Prim优化
        Queue<WeightedEdge> pq = new PriorityQueue<>();
        for (Integer w : G.adj(0)) {
            pq.add(new WeightedEdge(0, w, G.weight(0, w)));
        }

        while (!pq.isEmpty()) {
            WeightedEdge miniWeightedEdge = pq.remove();
            if (visited[miniWeightedEdge.getW()] && visited[miniWeightedEdge.getV()]) {
                continue;
            }
            mst.add(miniWeightedEdge);

            int newv = visited[miniWeightedEdge.getV()] ? miniWeightedEdge.getW() : miniWeightedEdge.getV();
            visited[newv] = true;
            for (Integer w : G.adj(newv)) {
                if (!visited[w]) {
                    pq.add(new WeightedEdge(newv, w, G.weight(newv, w)));
                }
            }
        }
    }

    public ArrayList<WeightedEdge> result() {
        return mst;
    }

    public static void main(String[] args) {
        String path = "D:/Lab/Data-Structure/Graph/prim/";
        WeightedGraph g = new WeightedGraph(path + "g.txt");
        Prim2 prim = new Prim2(g);
        System.out.println(prim.result());
    }
}
