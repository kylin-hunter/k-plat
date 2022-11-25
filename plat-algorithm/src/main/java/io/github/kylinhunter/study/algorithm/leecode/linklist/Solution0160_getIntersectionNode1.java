package io.github.kylinhunter.study.algorithm.leecode.linklist;

/**
 * 题目 ：相交链表
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。
 * 如果两个链表不存在相交节点，返回 null 。
 *
 * @author BiJi'an
 * @description
 * @date 2022-07-25 16:48
 **/
public class Solution0160_getIntersectionNode1 {

    /**
     * @param headA headA
     * @param headB headB
     * @return com.kylinhunter.plat.algorithm.study.leecode.linklist.ListNode
     * @title getIntersectionNode
     * @description 暴力求解，类似冒泡排序
     * @author BiJi'an
     * @date 2022-08-01 01:04
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode l1 = headA;
        while (l1 != null) {

            ListNode l2 = headB;

            while (l2 != null) {

                if (l1 == l2) {
                    return l2;
                }

                l2 = l2.next;
            }

            l1 = l1.next;
        }
        return l1;
    }

}
