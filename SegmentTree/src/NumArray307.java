class NumArray307 {


    private SegmentTree<Integer> segmentTree;

    public NumArray307(int[] nums) {
        if (nums.length > 0) {
            Integer[] data = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++) {
                data[i] = nums[i];
            }
            segmentTree = new SegmentTree<>(data, ((a, b) -> a + b));
        }
    }

    public void update(int index, int val) {
        if (segmentTree == null) {
            throw new IllegalArgumentException("error");
        }
        segmentTree.set(index, val);
    }

    public int sumRange(int left, int right) {
        if (segmentTree == null) {
            throw new IllegalArgumentException("segment tree is null");
        }
        return segmentTree.query(left, right);
    }
}
