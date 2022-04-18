class NumArray2 {

    private int[] sum;

    public NumArray2(int[] nums) {
        sum = new int[nums.length + 1];
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
    }

    public int sumRange(int left, int right) {
        return sum[right + 1] - sum[left];
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray2 numArray2 = new NumArray2(nums);
        System.out.println(numArray2.sumRange(0, 2));
        System.out.println(numArray2.sumRange(2, 5));
        System.out.println(numArray2.sumRange(0, 5));
    }
}