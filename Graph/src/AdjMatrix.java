import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 邻接矩阵
 *
 * @author yaotailin
 */
public class AdjMatrix {
    private int V;
    private int E;
    private int[][] data;

    public AdjMatrix(String fileName) {
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)) {
            V = scanner.nextInt();
            if (V < 0) {
                throw new IllegalArgumentException("V must be non-negative");
            }
            data = new int[V][V];

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
                if (data[a][b] == 1) {
                    throw new IllegalArgumentException("Parallel  Edges are Detected");
                }
                data[a][b] = 1;
                data[b][a] = 1;
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
        return data[v][w] == 1;
    }

    public ArrayList<Integer> adj(int v) {
        validateVertex(v);
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (data[v][i] == 1) {
                res.add(i);
            }
        }
        return res;
    }

    public int degree(int v) {
        return adj(v).size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d\n", V, E));
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                sb.append(String.format("%d ", data[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        AdjMatrix adjMatrix = new AdjMatrix("D:\\Lab\\Data-Structure\\Graph\\g.txt");
        System.out.println(adjMatrix);

    }
}
