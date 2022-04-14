import java.util.StringJoiner;

/**
 * @author yaotailin
 */
public class SegmentTree<E> {
    private E[] tree;
    private E[] data;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        tree = (E[]) new Object[4 * arr.length];
        buildTree(0, 0, data.length - 1);
    }

    private void buildTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int leftIndex = leftChild(treeIndex);
        int rightIndex = rightChild(treeIndex);

        int mid = l + (r - l) / 2;
        buildTree(leftIndex, l, mid);
        buildTree(rightIndex, mid + 1, r);
        tree[treeIndex] = merger.merge(tree[leftIndex], tree[rightIndex]);
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("索引越界");
        }
        return data[index];
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(",", "[", "]");

        for (E e : tree) {
            if (e != null) {
                joiner.add(e.toString());
            } else {
                joiner.add("null");
            }
        }

        return joiner.toString();
    }
}
