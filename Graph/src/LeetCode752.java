import java.util.*;

/**
 * 打开转盘锁
 */
public class LeetCode752 {

    public int openLock(String[] deadends, String target) {
        HashSet<String> deadendSet = new HashSet<>();
        for (String deadend : deadends) {
            deadendSet.add(deadend);
        }
        if (deadendSet.contains(target)) {
            return -1;
        }
        if (deadendSet.contains("0000")) {
            return -1;
        }
        if ("0000".equals(target)) {
            return 0;
        }

        HashMap<String, Integer> visited = new HashMap<>();
        Queue<String> queue = new LinkedList<>();

        queue.add("0000");
        visited.put("0000", 0);
        while (!queue.isEmpty()) {
            String curs = queue.remove();
            char[] chars = curs.toCharArray();

            //八种拨法
            ArrayList<String> nexts = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                char o = chars[i];
                chars[i] = Character.forDigit((chars[i] - '0' + 1) % 10, 10);
                nexts.add(new String(chars));
                chars[i] = o;
                chars[i] = Character.forDigit((chars[i] - '0' + 9) % 10, 10);
                nexts.add(new String(chars));
                chars[i] = o;
            }

            for (String next : nexts) {
                if (!deadendSet.contains(next) && !visited.containsKey(next)) {
                    queue.add(next);
                    visited.put(next, visited.get(curs) + 1);
                    if (next.equals(target)) {
                        return visited.get(next);
                    }
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        String[] deadends = {"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";
        LeetCode752 leetCode752 = new LeetCode752();
        System.out.println(leetCode752.openLock(deadends, target));
    }
}
