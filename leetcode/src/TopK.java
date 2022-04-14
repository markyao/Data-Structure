import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringJoiner;

public class TopK {
    public int[] getLeastNumbers(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i : arr) {
            if (pq.size() < k) {
                pq.add(i);
            } else if (!pq.isEmpty() && i < pq.peek()) {
                pq.remove();
                pq.add(i);
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
        int[] arr = {0, 1, 2, 1};
        int k = 0;
        TopK topK = new TopK();
        int[] leastNumbers = topK.getLeastNumbers(arr, k);
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        for (int anInt : leastNumbers) {
            joiner.add(String.valueOf(anInt));
        }
        System.out.println(joiner);
    }
}
