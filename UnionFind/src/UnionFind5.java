/**
 * 路径压缩
 *
 * @author yaotailin
 */
public class UnionFind5 implements UF {
    private int[] parent;
    private int[] rank; // rank[i] 表示以i为根的集合所表示的树的层数

    public UnionFind5(int size) {
        this.parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            rank[i] = 1;
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
     *
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
        //根据两个元素所在的树的rank不同判断合并方向
        //将rank低的集合合并到rank高的集合上
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[qRoot] < rank[pRoot]) {
            parent[qRoot] = pRoot;
        } else {
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }
    }

    private int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p is  out of  range");
        }
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }
}
