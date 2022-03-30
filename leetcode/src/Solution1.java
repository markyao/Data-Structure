public class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        int[] idx = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    idx[0] = i;
                    idx[1] = j;
                    return idx;
                }
            }
        }
        return idx;
    }

    public static void main(String[] args) {
        int[] nums = {2, 5, 5, 11};
        int[] ints = new Solution1().twoSum(nums, 10);
        System.out.println(ints);

    }
}
