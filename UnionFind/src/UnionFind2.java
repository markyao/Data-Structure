/**
 * @author yaotailin
 */
public class UnionFind2 implements UF {
    private int[] parent;

    public UnionFind2(int size) {
        this.parent = new int[size];

        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 合并元素p和元素q所属集合
     * O(h)复杂度 ，h为树的高度
     * @param p
     * @param q
     */
    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        parent[pRoot] = qRoot;
    }

    private int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p is  out of  range");
        }
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }
}
