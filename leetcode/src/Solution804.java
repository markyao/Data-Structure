import java.util.TreeSet;

public class Solution804 {
    public int uniqueMorseRepresentations(String[] words) {
        String[] codes = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--",
                "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

        TreeSet<String> set = new TreeSet<>();
        for (String word : words) {
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < word.length(); i++) {
                buffer.append(codes[word.charAt(i) - 'a']);
            }
            set.add(buffer.toString());
        }
        return set.size();
    }

    public static void main(String[] args) {
        String[] words = {"gin", "zen", "gig", "msg"};
        Solution804 solution804 = new Solution804();
        System.out.println(solution804.uniqueMorseRepresentations(words));
    }
}
