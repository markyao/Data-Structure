import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 图广度优先
 *
 * @author yaotailin
 */
public class CycleDetectionBFS {

    private Graph G;
    private boolean[] visited;

    private int[] pre;
    private boolean hasCycle = false;

    public CycleDetectionBFS(Graph G) {
        this.G = G;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        Arrays.fill(pre, -1);

        for (int v = 0; v < G.V(); v++) {
            if (!visited[v]) {
                if (bfs(v)) {
                    hasCycle = true;
                    break;
                }
            }
        }

    }


    // 从顶点 v 开始，判断图中是否有环
    private boolean bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        pre[s] = s;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (Integer w : G.adj(v)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v;
                } else if (pre[v] != w) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {

        Graph g = new Graph("D:\\\\Lab\\\\Data-Structure\\\\Graph\\\\g.txt");
        CycleDetectionBFS cycleDetection = new CycleDetectionBFS(g);
        System.out.println(cycleDetection.hasCycle());

        Graph g2 = new Graph("D:\\\\Lab\\\\Data-Structure\\\\Graph\\\\g2.txt");
        CycleDetectionBFS cycleDetection2 = new CycleDetectionBFS(g2);
        System.out.println(cycleDetection2.hasCycle());

    }
}
