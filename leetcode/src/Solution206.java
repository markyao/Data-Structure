/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class Solution206 {
    /**
     * Definition for singly-linked list.
     */

    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    public  ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 从下一个节点开始递归
        ListNode reverse = reverseList(head.next);
        head.next.next = head;
        // 设置下一个节点的 next 为当前节点
        head.next = null;
        //把当前节点的 next 赋值为 null，避免循环引用
        return reverse;
    }
    public static void main(String[] args) {
        int[] nums = {1, 2};

        ListNode node = new ListNode(1, new ListNode(2,new ListNode(3,new ListNode(4))));

        Solution206 solution206 = new Solution206();
        solution206.reverseList(node);
        System.out.println(node);

    }
}
