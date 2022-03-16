import java.util.StringJoiner;

/**
 * @author yaotailin
 */
public class ArrayQueue<E> implements Queue<E> {
    private final Array<E> array;

    public ArrayQueue(int capacity) {
        array = new Array<>(capacity);
    }

    public ArrayQueue() {
        array = new Array<>();
    }

    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "front [ ", "] tail ");
        for (int i = 0; i < array.getSize(); i++) {
            joiner.add(array.get(i).toString());
        }
        return "Queue: " + joiner;
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>();

        for (int i = 0; i < 5; i++) {
            queue.enqueue(i);
            System.out.println(queue);
        }

        queue.dequeue();
        System.out.println(queue);
    }
}
