import java.util.ArrayList;
import java.util.Stack;

public class EulerLoop {

    private Graph G;

    public EulerLoop(Graph G) {
        this.G = G;
    }

    public boolean hasEulerLoop() {
        CC cc = new CC(G);
        if (cc.count() > 1) {
            return false;
        }
        for (int v = 0; v < G.V(); v++) {
            if (G.degree(v) % 2 == 1) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Integer> result() {
        ArrayList<Integer> res = new ArrayList<>();
        if (!hasEulerLoop()) return res;

        Graph g = (Graph) G.clone();

        Stack<Integer> stack = new Stack<>();
        int curv = 0;
        stack.push(curv);
        while (!stack.empty()) {
            if (g.degree(curv) != 0) {
                stack.push(curv);
                int w = g.adj(curv).iterator().next();
                g.removeEdge(curv, w);
                curv = w;
            } else {
                res.add(curv);
                curv = stack.pop();
            }
        }
        return res;
    }


    public static void main(String[] args) {
        String path = "D:/Lab/Data-Structure/Graph/hierholzer/";

        Graph g = new Graph(path + "g.txt");
        EulerLoop el = new EulerLoop(g);
        System.out.println(el.result());

        Graph g2 = new Graph(path+"g2.txt");
        EulerLoop el2 = new EulerLoop(g2);
        System.out.println(el2.result());
    }
}
