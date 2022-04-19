public class Main {

    public static void main(String[] args) {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};

        SegmentTree<Integer> tree = new SegmentTree<>(nums, ((a, b) -> a + b));
        System.out.println(tree);
        System.out.println(tree.query(0, 2));
        System.out.println(tree.query(2, 5));
        System.out.println(tree.query(0, 5));
        tree.set(0,-4);
        System.out.println(tree);
        System.out.println(tree.query(0, 2));
        System.out.println(tree.query(2, 5));
        System.out.println(tree.query(0, 5));

    }
}
