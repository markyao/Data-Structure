import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 图广度优先（联通分量及个数）
 *
 * @author yaotailin
 */
public class CCBFS {

    private Graph G;
    private int[] visited;

    private int ccCount = 0;


    public CCBFS(Graph G) {
        this.G = G;
        visited = new int[G.V()];
        Arrays.fill(visited, -1);
        for (int v = 0; v < G.V(); v++) {
            if (visited[v] == -1) {
                bfs(v, ccCount);
                ccCount++;
            }
        }

    }

    private void bfs(int s, int ccCount) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = ccCount;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (Integer w : G.adj(v)) {
                if (visited[w] == -1) {
                    queue.add(w);
                    visited[w] = ccCount;
                }
            }
        }
    }

    /**
     * 联通分量个数
     *
     * @return
     */
    public int count() {
        return ccCount;
    }

    public boolean isConnected(int v, int w) {
        G.validateVertex(v);
        G.validateVertex(w);
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
        for (int v = 0; v < G.V(); v++) {
            res[visited[v]].add(v);
        }
        return res;
    }


    public static void main(String[] args) {
        Graph g = new Graph("D:\\\\Lab\\\\Data-Structure\\\\Graph\\\\g.txt");
        CCBFS cc = new CCBFS(g);
        System.out.println(cc.count());

        System.out.println(cc.isConnected(0, 6));
        System.out.println(cc.isConnected(5, 6));

        ArrayList<Integer>[] comp = cc.components();
        for (int ccid = 0; ccid < comp.length; ccid++) {
            System.out.print(ccid + " : ");
            for (int w : comp[ccid])
                System.out.print(w + " ");
            System.out.println();
        }

    }
}
