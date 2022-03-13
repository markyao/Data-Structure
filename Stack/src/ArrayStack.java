import java.util.StringJoiner;

/**
 * @author yaotailin
 */
public class ArrayStack<E> implements Stack<E> {
    private Array array;

    public ArrayStack(int capacity) {
        array = new Array(capacity);
    }

    public ArrayStack() {
        array = new Array();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return (E) array.removeLast();
    }

    @Override
    public E peek() {
        return (E) array.getLast();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[ ", "] top");
        for (int i = 0; i < array.getSize(); i++) {
            joiner.add(array.get(i).toString());
        }
        return "Stack: " + joiner;
    }
}
