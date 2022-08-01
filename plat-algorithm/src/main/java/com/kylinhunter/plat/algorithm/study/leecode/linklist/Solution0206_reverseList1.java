package com.kylinhunter.plat.algorithm.study.leecode.linklist;

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
        ListNode node = head;
        ListNode result = head;

        ListNode nextNode = node.next;

        while (nextNode != null) {

            ListNode tmp = result;
            result = nextNode;

            nextNode = nextNode.next;

            result.next = tmp;

        }

        return node;
    }

}
