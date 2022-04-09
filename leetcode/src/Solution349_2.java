import java.util.ArrayList;
import java.util.TreeSet;

public class Solution349_2 {
    public static int[] intersection(int[] nums1, int[] nums2) {
        TreeSet<Integer> set  =new TreeSet<>();
        for (int i : nums1) {
            set.add(i);
        }
        ArrayList<Integer> list =new ArrayList<>();
        for (int i : nums2) {
            if(set.contains(i)){
                list.add(i);
                set.remove(i);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
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
