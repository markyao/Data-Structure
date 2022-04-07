import java.util.StringJoiner;

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


    public void add(int index, E e) {
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
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public void removeElement(E e) {
        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.e.equals(e)) {
                break;
            }
            prev = prev.next;
        }
        if(prev.next != null){
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size --;
        }
    }

    public E remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Illegal index.");
        }

        Node pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        Node retNode = pre.next;
        pre.next = retNode.next;
        retNode.next = null;
        size--;
        return retNode.e;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public String toString() {

        Node cur = dummyHead.next;
        StringJoiner joiner = new StringJoiner("->", "", "->NULL ");
        for (int i = 0; i < size; i++) {
            joiner.add(cur.toString());
            cur = cur.next;
        }
        return joiner.toString();
    }

    private Node getNode(int index) {
        Node cur = dummyHead.next;
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Illegal index.");
        }
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur;
    }
}
