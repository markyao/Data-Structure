import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
        size++;
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

    public void preOrderNR() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            Node node = stack.pop();
            System.out.println(node.e);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    public void inOrder() {
        inOrder(root);
    }

    //中顺遍历
    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    public void postOrder() {
        postOrder(root);
    }

    //后顺遍历
    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            System.out.println(cur.e);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    public E minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }
        return minimum(root).e;
    }

    /**
     * 最小值
     *
     * @param node
     * @return
     */
    private Node<E> minimum(Node<E> node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    public E maximum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }
        return maximum(root).e;
    }

    /**
     * 最大值
     *
     * @param node
     * @return
     */
    private Node<E> maximum(Node<E> node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    public E removeMin() {
        E e = minimum();
        root = removeMin(root);
        return e;
    }

    private Node removeMin(Node<E> node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public E removeMax() {
        E e = maximum();
        root = removeMax(root);
        return e;
    }

    private Node removeMax(Node<E> node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    public void remove(E e) {
        root = remove(root, e);
    }

    private Node<E> remove(Node<E> node, E e) {
        if (node == null) {
            return null;
        }

        if (e.compareTo(node.e) < 0) {
            return getNode(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            return getNode(node.right, e);
        } else {
            if (node.left == null) {
                Node nodeRight = node.right;
                node.right = null;
                size--;
                return nodeRight;
            }
            if (node.right == null) {
                Node nodeLeft = node.left;
                node.left = null;
                size--;
                return nodeLeft;
            }

            Node s = minimum(node.right);
            s.right = removeMin(node.right);
            s.left = node.left;
            node.left = node.right = null;
            return s;
        }
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
        bst.preOrderNR();
        System.out.println();
        bst.inOrder();
        System.out.println();
        bst.postOrder();
        System.out.println();
        bst.levelOrder();
        System.out.println();
        System.out.println(bst.minimum());
        System.out.println(bst.maximum());
        System.out.println();
        System.out.println(bst.removeMin());
        System.out.println(bst.removeMax());
        bst.levelOrder();
    }
}
