import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class LeetCode1192 {
    private boolean[] visited;

    private int[] ord;
    private int[] low;
    private int cnt;
    private HashSet<Integer>[] G;
    List<List<Integer>> list = new ArrayList<>();

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {

        if (n == 1 || connections.isEmpty()) {
            return list;
        }
        G = constructGraph(n, connections);
        visited = new boolean[n];
        ord = new int[n];
        low = new int[n];

        cnt = 0;

        for (int v = 0; v < n; v++) {
            if (!visited[v]) {
                dfs(v, v);
            }
        }
        return list;
    }

    private HashSet<Integer>[] constructGraph(int n, List<List<Integer>> connections) {
        HashSet<Integer>[] g = new HashSet[n];
        for (int i = 0; i < n; i++) {
            g[i] = new HashSet<>();
        }

        for (List<Integer> connection : connections) {
            Integer v1 = connection.get(0);
            Integer v2 = connection.get(1);
            g[v1].add(v2);
            g[v2].add(v1);
        }
        return g;
    }

    private void dfs(int v, int parent) {
        visited[v] = true;
        ord[v] = cnt;
        low[v] = ord[v];
        cnt++;
        for (Integer w : G[v]) {
            if (!visited[w]) {
                dfs(w, v);
                low[v] = Math.min(low[v], low[w]);
                if (low[w] > ord[v]) {
                    ArrayList<Integer> res = new ArrayList<>();
                    res.add(v);
                    res.add(w);
                    list.add(res);
                }
            } else if (w != parent) {
                low[v] = Math.min(low[v], low[w]);
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> connections = new ArrayList<>();
//        int n = 4;
//        List<List<Integer>> connections = new ArrayList<>();
//        connections.add(Arrays.asList(0, 1));
//        connections.add(Arrays.asList(1, 2));
//        connections.add(Arrays.asList(2, 0));
//        connections.add(Arrays.asList(1, 3));

        int n=2;
//        connections.add(new ArrayList<>());
        LeetCode1192 leetCode1192 = new LeetCode1192();
        System.out.println(leetCode1192.criticalConnections(n, connections));
    }
}
