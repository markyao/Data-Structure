import java.util.ArrayList;

/**
 * 红黑树
 *
 * @param <K>
 * @param <V>
 * @author yaotailin
 */
public class RBTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;
    private int size;


    public RBTree() {
        root = null;
        size = 0;
    }

    // 判断节点node的颜色
    private boolean isRed(Node node) {
        if (node == null) {
            return BLACK;
        }
        return node.color;
    }

    //   node                     x
    //  /   \     左旋转         /  \
    // T1   x   --------->   node   T3
    //     / \              /   \
    //    T2 T3            T1   T2
    private Node leftRotate(Node node) {
        Node x = node.right;

        //左旋转
        node.right = x.left;
        x.left = node;

        x.color = node.color;
        node.color = RED;
        return x;
    }

    //     node                   x
    //    /   \     右旋转       /  \
    //   x    T2   ------->   y   node
    //  / \                       /  \
    // y  T1                     T1  T2
    private Node rightRotate(Node node) {
        Node x = node.left;

        // 右旋转
        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    //颜色翻转
    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }


    public void add(K key, V value) {
        root = add(root, key, value);
        root.color = BLACK;
        size++;
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }

        if(isRed(node.right) && !isRed(node.left)){
            node = leftRotate(node);
        }

        if(isRed(node.left)  && isRed(node.left.left)){
            node = rightRotate(node);
        }
        if(isRed(node.left) && isRed(node.right)){
            flipColors(node);
        }
        return node;
    }

    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            return getNode(node.right, key);
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

    /**
     * 最小值
     *
     * @param node
     * @return
     */
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }


    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }


    public boolean contains(K key) {
        return get(key) != null;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V value) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + " doesn't exists");
        } else {
            node.value = value;
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) == 0) {
            return node;
        }
        if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else {
            //if (e.compareTo(node.e) > 0) {
            return getNode(node.right, key);
        }
    }

    private class Node {
        private K key;
        private V value;
        private Node left, right;
        private boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color = RED;
        }
    }

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");
        ArrayList<String> words = new ArrayList<>();

        String filename = "D:\\Lab\\Data-Structure\\Map\\src\\pride-and-prejudice.txt";
        if (FileOperation.readFile(filename, words)) {
            System.out.println("Total world:" + words.size());
            RBTree<String, Integer> map = new RBTree<>();
            for (String word : words) {
                if (map.contains(word)) {
                    map.set(word, map.get(word) + 1);
                } else {
                    map.add(word, 1);
                }
            }
            System.out.println("Total different world:" + map.getSize());
            System.out.println("Frequency of PRIDE:" + map.get("pride"));

        }
    }
}
