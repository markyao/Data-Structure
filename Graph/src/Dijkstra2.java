import java.util.*;

/**
 * 最短距离算法 dijkstra
 */
public class Dijkstra2 {

    private WeightedGraph G;
    private int s;
    private int[] dis;
    private boolean[] visited;
    private int[] pre;

    private class Node implements Comparable<Node> {
        public int v, dis;

        public Node(int v, int dis) {
            this.v = v;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node o) {
            return this.dis - o.dis;
        }
    }

    public Dijkstra2(WeightedGraph G, int s) {
        this.G = G;
        this.s = s;

        G.validateVertex(s);
        dis = new int[G.V()];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[s] = 0;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        Arrays.fill(pre, -1);

        Queue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s, 0));

        while (!pq.isEmpty()) {
            int cur = pq.remove().v;
            if (visited[cur]) {
                continue;
            }
            visited[cur] = true;
            for (Integer w : G.adj(cur)) {
                if (!visited[w]) {
                    if (dis[cur] + G.weight(cur, w) < dis[w]) {
                        dis[w] = dis[cur] + G.weight(cur, w);
                        pq.add(new Node(w, dis[w]));
                        pre[w] = cur;
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

    public Iterable<Integer> path(int t) {
        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnectedTo(t)) {
            return res;
        }
        int cur = t;
        while (cur != s) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        String path = "/Users/yaotailin/IdeaProjects/Lab/Data-Structure/Graph/dijkstra/";
        WeightedGraph g = new WeightedGraph(path + "g.txt");
        Dijkstra2 dijkstra = new Dijkstra2(g, 0);
        for (int v = 0; v < g.V(); v++) {
            System.out.print(dijkstra.disTo(v) + " ");
        }
        System.out.println();
        System.out.println(dijkstra.path(3));
    }
}
