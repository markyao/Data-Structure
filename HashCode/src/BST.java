import java.util.ArrayList;


public class BST<K extends Comparable<K>, V> {

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public void add(K key, V value) {
        root = add(root, key, value);
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

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");
        ArrayList<String> words = new ArrayList<>();

        String filename = "D:\\Lab\\Data-Structure\\Map\\src\\pride-and-prejudice.txt";
        if (FileOperation.readFile(filename, words)) {
            System.out.println("Total world:" + words.size());
            BST<String, Integer> map = new BST<>();
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
