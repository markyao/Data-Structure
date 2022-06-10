import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * 邻接矩阵
 *
 * @author yaotailin
 */
public class AdjSet {
    private int V;
    private int E;
    private TreeSet<Integer>[] data;

    public AdjSet(String fileName) {
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)) {
            V = scanner.nextInt();
            if (V < 0) {
                throw new IllegalArgumentException("V must be non-negative");
            }
            data = new TreeSet[V];

            for (int i = 0; i < V; i++) {
                data[i] = new TreeSet();
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

                if (a == b) {
                    throw new IllegalArgumentException("self Loop is Detected");
                }
                if (data[a].contains(b)) {
                    throw new IllegalArgumentException("Parallel  Edges are Detected");
                }
                data[a].add(b);
                data[b].add(a);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validateVertex(int v) {
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
        return data[v].contains(w);
    }

    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return data[v];
    }

    public int degree(int v) {
        validateVertex(v);
        return data[v].size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d\n", V, E));
        for (int v = 0; v < V; v++) {
            sb.append(String.format("%d: ", v));
            for (int w : data[v]) {
                sb.append(String.format("%d ", w));
            }
            sb.append("\n");
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        AdjSet adjList = new AdjSet("D:\\Lab\\Data-Structure\\Graph\\g.txt");
        System.out.println(adjList);

    }
}
