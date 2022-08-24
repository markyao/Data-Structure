import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * 邻接矩阵
 * 暂时只支持无向带权图
 *
 * @author yaotailin
 */
public class WeightedGraph {
    private int V;
    private int E;
    private TreeMap<Integer, Integer>[] adj;

    public WeightedGraph(String fileName) {
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)) {
            V = scanner.nextInt();
            if (V < 0) {
                throw new IllegalArgumentException("V must be non-negative");
            }
            adj = new TreeMap[V];

            for (int i = 0; i < V; i++) {
                adj[i] = new TreeMap();
            }

            E = scanner.nextInt();
            if (E < 0) {
                throw new IllegalArgumentException("E must be non-negative");
            }
            for (int i = 0; i < E; i++) {
                int a = scanner.nextInt();
                validateVertex(a);
                int b = scanner.nextInt();
                validateVertex(b);

                int weight = scanner.nextInt();

                if (a == b) {
                    throw new IllegalArgumentException("self Loop is Detected");
                }
                if (adj[a].containsKey(b)) {
                    throw new IllegalArgumentException("Parallel  Edges are Detected");
                }
                adj[a].put(b, weight);
                adj[b].put(a, weight);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is invalid");
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return adj[v].containsKey(w);
    }


    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v].keySet();
    }

    public int weight(int v, int w) {
        if (hasEdge(v, w)) {
            return adj[v].get(w);
        }
        throw new IllegalArgumentException(String.format("No edge %d-%d", v, w));
    }

    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("V = %d, E = %d\n", V, E));
        for (int v = 0; v < V; v++) {
            sb.append(String.format("%d : ", v));
            for (Map.Entry<Integer, Integer> entry : adj[v].entrySet()) {
                sb.append(String.format("(%d：%d)", entry.getKey(), entry.getValue()));
            }
            sb.append('\n');
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        WeightedGraph adjList = new WeightedGraph("D:\\Lab\\Data-Structure\\Graph\\weight\\g.txt");
        System.out.println(adjList);

    }
}
