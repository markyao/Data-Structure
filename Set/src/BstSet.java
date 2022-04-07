/**
 * @author yaotailin
 */
public class BstSet<E extends Comparable> implements Set<E> {
    private BST bst;

    public BstSet() {
        bst = new BST();
    }

    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contanis(e);
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

}
