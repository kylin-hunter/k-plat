package io.github.kylinhunter.study.algorithm.leecode.linklist;

/**
 * 题目 ：回文链表
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表
 * 。如果是，返回 true ；否则，返回 false 。
 *
 * @author BiJi'an
 * @description
 * @date 2022-07-25 16:48
 **/
public class Solution0234_isPalindrome2 {

    public boolean isPalindrome(ListNode head) {

        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        if (fast != null) {
            slow = slow.next;
        }
        slow = reverse(slow);
        fast = head;

        while (slow != null) {
            if (slow.val != fast.val) {
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }

        return true;
    }

    private ListNode reverse(ListNode head) {
        ListNode preNode = null;
        ListNode nowNode = head;

        while (nowNode != null) {

            ListNode tmp = nowNode.next;
            nowNode.next = preNode;
            preNode = nowNode;

            nowNode = tmp;

        }
        return preNode;

    }

}
