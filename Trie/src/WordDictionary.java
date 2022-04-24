import java.util.TreeMap;

/**
 * 211. 添加与搜索单词 - 数据结构设计
 */
class WordDictionary {
    private Node root;

    public WordDictionary() {
        root = new Node();
    }

    public void addWord(String word) {
        addWord(root, word, 0);
    }

    private void addWord(Node node, String word, int index) {
        if (index == word.length()) {
            node.isword = true;
            return;
        }
        char c = word.charAt(index);
        if (node.next.get(c) == null) {
            node.next.put(c, new Node());
        }
        addWord(node.next.get(c), word, index + 1);

    }

    public boolean search(String word) {
        return match(root, word, 0);
    }

    private boolean match(Node node, String word, int index) {
        if (index == word.length()) {
            return node.isword;
        }
        char c = word.charAt(index);
        if (c != '.') {
            if (node.next.get(c) == null) {
                return false;
            }
            return match(node.next.get(c), word, index + 1);
        } else {
            for (Character nextChar : node.next.keySet()) {
                if (match(node.next.get(nextChar), word, index + 1)) {
                    return true;
                }
            }
            return false;
        }

    }

    private class Node {
        public boolean isword;
        public TreeMap<Character, Node> next;

        public Node(boolean isword) {
            this.isword = isword;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("bac");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
//        System.out.println(wordDictionary.search("pad")); // 返回 False
//        System.out.println(wordDictionary.search("bad")); // 返回 True
//        System.out.println(wordDictionary.search(".ad")); // 返回 True
        System.out.println(wordDictionary.search("b..")); // 返回 True


    }

}
