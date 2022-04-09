import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution349 {
    public static int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> list1 = Arrays.stream(nums1).boxed().distinct().collect(Collectors.toList());
        List<Integer> list2 = Arrays.stream(nums2).boxed().distinct().collect(Collectors.toList());
        List<Integer> integers = list1.stream().filter(item -> list2.contains(item)).collect(Collectors.toList());
        int[] res = new int[integers.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = integers.get(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {4,9,5};
        int[] nums2 = {9,4,9,8,4};
        int[] intersection = intersection(nums1, nums2);
        for (int i : intersection) {
            System.out.println(i);
        }
    }
}
