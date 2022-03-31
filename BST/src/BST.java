public class BST<E extends Comparable<E>> {

    private class Node<E> {
        private E e;
        private Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }

        private Node root;
        private int size;

        public int getSize() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }
}
