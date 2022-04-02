import java.util.StringJoiner;

public class BST<E extends Comparable<E>> {

    private class Node<E> {
        private E e;
        private Node<E> left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node<E> root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public void add(E e) {
        root = add(root, e);
    }

    /**
     * 复杂写法
     *
     * @param node
     * @param e
     */
    private void add2(Node<E> node, E e) {
        if (node.e == e) {
            return;
        } else if (e.compareTo(node.e) < 0 && node.left == null) {
            node.left = new Node<>(e);
            size++;
            return;
        } else if (e.compareTo(node.e) > 0 && node.right == null) {
            node.right = new Node<>(e);
            size++;
            return;
        }

        if (e.compareTo(node.e) < 0) {
            add(node.left, e);
        } else {
            add(node.right, e);
        }
    }

    private Node<E> add(Node<E> node, E e) {
        if (node == null) {
            return new Node<>(e);
        }

        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }
        return node;
    }

    public boolean contanis(E e) {
        return getNode(root, e) != null ? true : false;
    }

    private Node getNode(Node<E> node, E e) {
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e) == 0) {
            return node;
        }
        if (e.compareTo(node.e) < 0) {
            node = getNode(node.left, e);
        } else {
            //if (e.compareTo(node.e) > 0) {
            node = getNode(node.right, e);
        }
        return node;
    }

    public void preOrder() {
        preOrder(root);
    }

    //前顺遍历
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    private void generateBSTString(Node<E> node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }
        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num : nums) {
            bst.add(num);
        }
        bst.preOrder();
        System.out.println();
        System.out.println(bst);
    }
}
