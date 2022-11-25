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
public class Solution0160_getIntersectionNode3 extends Solution0160_getIntersectionNode1 {
    /**
     * @param headA headA
     * @param headB headB
     * @return com.kylinhunter.plat.algorithm.study.leecode.linklist.ListNode
     * @title getIntersectionNode
     * @description 互相指向对方的表头，二次遍历，有点技巧的
     * @author BiJi'an
     * @date 2022-08-01 01:04
     */
    @Override
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode l1 = headA;
        ListNode l2 = headB;
        while (l1 != l2) {
            l1 = l1 == null ? headB : l1.next;
            l2 = l2 == null ? headA : l2.next;
        }
        return l1;
    }

}
