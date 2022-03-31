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

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
