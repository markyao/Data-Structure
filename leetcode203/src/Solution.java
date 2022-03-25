class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        while (head.val == val) {
//            ListNode delNode = head;
//            head = head.next;
//            delNode.next = null;
            head = head.next;
        }

        ListNode pre = head;
        while (pre.next != null) {
            if (pre.next.val == val) {
//                ListNode delNode = pre.next;
//                pre.next = delNode.next;
//                delNode.next = null;
                pre.next = pre.next.next;

            }else {
                pre = pre.next;
            }
        }
        return head;
    }
}

