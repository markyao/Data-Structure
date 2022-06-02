import java.io.File;
import java.io.IOException;
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
            data = new int[V][V];

            E = scanner.nextInt();
            for (int i = 0; i < E; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                data[a][b] = 1;
                data[b][a] = 1;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
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
