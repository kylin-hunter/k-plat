package io.github.kylinhunter.study.algorithm.leecode.linklist;

/**
 * 题目 ：翻转链表
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 *
 * @author BiJi'an
 * @description
 * @date 2022-07-25 16:48
 **/
public class Solution0206_reverseList1 {

    public ListNode reverseList(ListNode head) {
        ListNode preNode = null;
        ListNode node = head;

        while (node != null) {

            ListNode tmp = node.next;
            node.next = preNode;

            preNode = node;

            node = tmp;

        }

        return preNode;
    }

}
