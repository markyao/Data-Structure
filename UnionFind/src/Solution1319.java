/**
 * leetcode 1319
 *
 * @author yaotailin
 */
public class Solution1319 {

    public int makeConnected(int n, int[][] connections) {
        if (connections.length < (n - 1)) {
            return -1;
        }
        UnionFind uf = new UnionFind(n);
        for (int[] connection : connections) {
            uf.union(connection[0], connection[1]);

        }
        return uf.num-1;
    }


    private class UnionFind {
        private int[] parent;
        private int[] rank; // rank{i} 表示以i为根的集合所表示的树的层数
        private int num;

        public UnionFind(int size) {
            this.parent = new int[size];
            rank = new int[size];
            num = size;
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        /**
         * 合并元素p和元素q所属集合
         * O(h)复杂度 ，h为树的高度
         *
         * @param p
         * @param q
         */
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
            num--;
        }

        /**
         * 递归压缩
         *
         * @param p
         * @return
         */
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

    public static void main(String[] args) {
        Solution1319 solution1319 = new Solution1319();
//        int{}{} connections = {{0, 1}, {0, 2}, {1, 2}};
//        System.out.println(solution1319.makeConnected(4, connections));

//        int{}{} connections = {{0,1},{0,2},{0,3},{1,2},{1,3}};
//        System.out.println(solution1319.makeConnected(6, connections));

//        int{}{} connections = {{0,1},{0,2},{0,3},{1,2}};
//        System.out.println(solution1319.makeConnected(6, connections));

//        int{}{} connections = {{0,1},{0,2},{3,4},{2,3}};
//        System.out.println(solution1319.makeConnected(5, connections));

        int[][] connections = {{1, 5}, {1, 7}, {1, 2}, {1, 4}, {3, 7}, {4, 7}, {3, 5}, {0, 6}, {0, 1}, {0, 4}, {2, 6}, {0, 3}, {0, 2}};
        System.out.println(solution1319.makeConnected(12, connections));
    }
}
