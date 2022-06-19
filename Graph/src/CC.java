import java.util.ArrayList;
import java.util.Arrays;

/**
 * 图深度优先遍历
 *
 * @author yaotailin
 */
public class CC {

    private Graph graph;
    private int[] visited;
    private int ccCount = 0;

    public CC(Graph graph) {
        this.graph = graph;
        visited = new int[graph.V()];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = -1;
        }
        for (int v = 0; v < graph.V(); v++) {
            if (visited[v] == -1) {
                dfs(v, ccCount);
                ccCount++;
            }
        }
    }

    private void dfs(int v, int ccid) {
        visited[v] = ccid;
        for (Integer w : graph.adj(v)) {
            if (visited[w] == -1) {
                dfs(w, ccid);
            }
        }
    }

    public int count() {
        System.out.println(Arrays.toString(visited));
        return ccCount;
    }

    /**
     * 两个顶点是否连接
     *
     * @param v
     * @param w
     * @return
     */
    public boolean isConnected(int v, int w) {
        graph.validateVertex(v);
        graph.validateVertex(w);
        return visited[v] == visited[w];
    }

    /**
     * 图中联通分量
     *
     * @return
     */
    public ArrayList<Integer>[] components() {
        ArrayList<Integer>[] res = new ArrayList[ccCount];
        for (int i = 0; i < res.length; i++) {
            res[i] = new ArrayList<>();
        }
        for (int v = 0; v < graph.V(); v++) {
            res[visited[v]].add(v);
        }
        return res;
    }


    public static void main(String[] args) {
        Graph graph = new Graph("D:\\\\Lab\\\\Data-Structure\\\\Graph\\\\g.txt");
        CC cc = new CC(graph);
        System.out.println(cc.count());

        System.out.println(cc.isConnected(0, 6));
        System.out.println(cc.isConnected(0, 5));

        ArrayList<Integer>[] components = cc.components();
        for (int ccid = 0; ccid < components.length; ccid++) {
            System.out.print(ccid + " : ");
            for (Integer w : components[ccid]) {
                System.out.print(w + " ");
            }
            System.out.println();
        }
    }
}
