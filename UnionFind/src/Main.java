import java.util.Random;

public class Main {

    public static double TestUF(UF uf, int m) {
        int size = uf.getSize();
        Random random = new Random();
        long startTime = System.nanoTime();
        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.union(a, b);
        }
        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a, b);
        }
        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000_000_000.0;
    }

    public static void main(String[] args) {
        int size = 10000000;
        int m = 10000000;
//        UnionFind1 uf1 = new UnionFind1(size);
//        System.out.println("UUnionFind1: " + TestUF(uf1, m) + " s");
//        UnionFind2 uf2 = new UnionFind2(size);
//        System.out.println("UUnionFind2: " + TestUF(uf2, m) + " s");
        UnionFind3 uf3 = new UnionFind3(size);
        System.out.println("UUnionFind3: " + TestUF(uf3, m) + " s");
        UnionFind4 uf4 = new UnionFind4(size);
        System.out.println("UUnionFind4: " + TestUF(uf4, m) + " s");
    }
}
