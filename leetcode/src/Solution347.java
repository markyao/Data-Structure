import java.util.*;

class Solution347 {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(map::get));

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (pq.size() < k) {
                pq.add(entry.getKey());
            } else if (map.get(pq.peek()) < entry.getValue()) {
                pq.remove();
                pq.add(entry.getKey());
            }
        }

        int[] res = new int[pq.size()];
        int idx = 0;
        while (!pq.isEmpty()) {
            res[idx] = pq.remove();
            idx++;
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {3, 0, 1, 0};
        int k = 1;
        Solution347 solution347 = new Solution347();
        int[] ints = solution347.topKFrequent(nums, k);

        StringJoiner joiner = new StringJoiner(",", "[", "]");
        for (int anInt : ints) {
            joiner.add(String.valueOf(anInt));
        }
        System.out.println(joiner);
    }
}
