import java.util.StringJoiner;

public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("array can not be empty");
        }
        val = arr[0];
        ListNode cur = this;
        for (int i = 1; i < arr.length; i++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
    }

    ListNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("->", "", "->NULL");
        ListNode cur = this;
        while (cur != null) {
            joiner.add(String.valueOf(cur.val));
            cur = cur.next;
        }
        return joiner.toString();
    }
}
