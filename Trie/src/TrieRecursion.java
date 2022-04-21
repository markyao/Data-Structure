import java.util.TreeMap;

/**
 * @author yaotailin
 */
public class TrieRecursion {
    private Node root;
    private int size;

    public TrieRecursion() {
        root = new Node();
        size = 0;
    }

    public void add(String word) {
        add(root, word, 0);
    }

    public void add(Node node, String word, int index) {
        if (index == word.length()) {
            if (!node.isWord) {
                node.isWord = true;
                size++;
            }
            return;
        }
        char c = word.charAt(index);
        if (node.next.get(c) == null) {
            node.next.put(c, new Node());
        }
        add(node.next.get(c), word, index++);
    }

    public boolean contains(String word) {
        return contains(root, word, 0);
    }

    public boolean contains(Node node, String word, int index) {
        if (index == word.length()) {
            return node.isWord;
        }
        char c = word.charAt(index);
        if (node.next.get(c) == null) {
            return false;
        }
        return contains(node.next.get(c), word, index++);
    }

    public int getSize() {
        return size;
    }

    private class Node {
        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            this.next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

}
