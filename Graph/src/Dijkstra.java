import java.util.Arrays;

/**
 * 最短距离算法 dijkstra
 */
public class Dijkstra {

    private WeightedGraph G;
    private int s;
    private int[] dis;
    private boolean[] visited;

    public Dijkstra(WeightedGraph G, int s) {
        this.G = G;
        this.s = s;

        G.validateVertex(s);
        dis = new int[G.V()];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[s] = 0;
        visited = new boolean[G.V()];

        while (true) {
            int curdis = Integer.MAX_VALUE, cur = -1;
            for (int v = 0; v < G.V(); v++) {
                if (!visited[v] && dis[v] < curdis) {
                    curdis = dis[v];
                    cur = v;
                }
            }
            if (cur == -1) {
                break;
            }

            visited[cur] = true;
            for (Integer w : G.adj(cur)) {
                if (!visited[w]) {
                    if (dis[cur] + G.weight(cur, w) < dis[w]) {
                        dis[w] = dis[cur] + G.weight(cur, w);
                    }
                }
            }
        }
    }

    /**
     * 是否连接
     *
     * @param v
     * @return
     */
    public boolean isConnectedTo(int v) {
        G.validateVertex(v);
        return visited[v];
    }

    /**
     * 原点到v的距离
     *
     * @param v
     * @return
     */
    public int disTo(int v) {
        G.validateVertex(v);
        return dis[v];
    }

    public static void main(String[] args) {
        String path = "/Users/yaotailin/IdeaProjects/Lab/Data-Structure/Graph/dijkstra/";
        WeightedGraph g = new WeightedGraph(path + "g.txt");
        Dijkstra dijkstra = new Dijkstra(g, 0);
        for (int v = 0; v < g.V(); v++) {
            System.out.print(dijkstra.disTo(v) + " ");
        }
    }
}
