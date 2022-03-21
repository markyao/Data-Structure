/**
 * @author yaotailin
 */
public class LinkedList<E> {

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

    private final Node dummyHead;
    private int size;

    public LinkedList() {
        dummyHead = new Node(null, null);
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    private void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Illegal index.");
        }

        Node pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
//            Node node = new Node(e);
//            node.next = pre.next;
//            pre.next = node;

        pre.next = new Node(e, pre.next);
        size++;

    }

    public void addFirst(E e) {
        add(0, e);
    }


    public void addLast(E e) {
        add(size, e);
    }
}
