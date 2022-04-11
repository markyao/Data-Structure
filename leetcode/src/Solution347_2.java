import java.util.*;

class Solution347_2 {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.stream(nums).forEach(num -> map.put(num, map.getOrDefault(num, 0) + 1));
        int[] res = map.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue())
                .limit(k).mapToInt(Map.Entry::getKey).toArray();
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {3, 0, 1, 0};
        int k = 1;
        Solution347_2 solution347 = new Solution347_2();
        int[] ints = solution347.topKFrequent(nums, k);

        StringJoiner joiner = new StringJoiner(",", "[", "]");
        for (int anInt : ints) {
            joiner.add(String.valueOf(anInt));
        }
        System.out.println(joiner);
    }
}
