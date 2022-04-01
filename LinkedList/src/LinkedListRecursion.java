import java.util.StringJoiner;

/**
 * @author yaotailin
 */
public class LinkedListRecursion<E> {

    private Node head;
    private int size;

    public LinkedListRecursion() {
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Illegal index.");
        }
        head = add(head, index, e);
        size++;
    }

    private Node add(Node node, int index, E e) {
        if (index == 0) {
            return new Node(e, node);
        }
        node.next = add(node.next, --index, e);
        return node;
    }

    public void addFirst(E e) {
        add(0, e);
    }


    public void addLast(E e) {
        add(size, e);
    }

    public E get(int index) {
        Node node = getNode(index);
        return node.e;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public void set(int index, E e) {
        Node node = getNode(index);
        node.e = e;
    }

    public boolean contains(E e) {
        return contains(head, e);
    }

    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (node.e.equals(e)) {
            return true;
        }
        return contains(node.next, e);

    }

    public void removeElement(E e) {
        head = removeElement(head, e);
        size--;
    }

    private Node removeElement(Node node, E e) {
        if (node == null) {
            return null;
        }
        if (node.e.equals(e)) {
            return node.next;
        }

        node.next = removeElement(node.next, e);
        return node;
    }

    public E remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Illegal index.");
        }

        Pair<Node, E> pair = remove(head, index);
        head = pair.getKey();
        size--;
        return pair.value;
    }

    private Pair<Node, E> remove(Node node, int index) {
        if (index == 0) {
            return new Pair<>(node.next, node.e);
        }
        Pair<Node, E> res = remove(node.next, index - 1);
        node.next = res.getKey();
        return new Pair<>(node, res.value);
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public String toString() {

        Node cur = head;
        StringJoiner joiner = new StringJoiner("->", "", "->NULL ");
        for (int i = 0; i < size; i++) {
            joiner.add(cur.toString());
            cur = cur.next;
        }
        return joiner.toString();
    }

    private Node getNode(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Illegal index.");
        }
        return getNode(head, index);
    }

    private Node getNode(Node node, int index) {
        if (index == 0) {
            return node;
        }
        node = getNode(node.next, --index);
        return node;
    }

    private class Pair<Node, E> {
        private Node key;
        private E value;

        public Pair(Node key, E value) {
            this.key = key;
            this.value = value;
        }

        public Node getKey() {
            return key;
        }

        public void setKey(Node key) {
            this.key = key;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }
    }

    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    public static void main(String[] args) {
        LinkedListRecursion<Integer> linkedList = new LinkedListRecursion<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        linkedList.add(2, 666);
        System.out.println(linkedList);
        System.out.println(linkedList.getFirst());
        System.out.println(linkedList.getLast());
        System.out.println(linkedList);
        linkedList.removeFirst();
        System.out.println(linkedList);
        linkedList.removeLast();
        System.out.println(linkedList);
        System.out.println(linkedList.contains(666));
        linkedList.removeElement(666);
        System.out.println(linkedList);

    }
}
