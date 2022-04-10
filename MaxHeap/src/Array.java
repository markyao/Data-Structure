import java.util.StringJoiner;

/**
 * 数组
 *
 * @author yaotailin
 */
public class Array<E> {
    private E[] data;
    private int size;

    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public Array() {
        this(10);
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("索引越界");
        }
        //扩容
        if (size == data.length) {
            resize(2 * data.length);
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    E get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("索引越界");
        }
        return data[index];
    }

    void set(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("索引越界");
        }
        data[index] = e;
    }

    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    public E remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("索引越界");
        }
        E ret = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        data[size] = null;
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return ret;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public void removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }


    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[ ", "]");
        for (int i = 0; i < size; i++) {
            joiner.add(data[i].toString());
        }
        return String.format("Array: Size = %d, capacity = %d\n", size, data.length) + joiner;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];

//        for (int i = 0; i < size; i++) {
//            newData[i] = data[i];
//        }
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }
}
