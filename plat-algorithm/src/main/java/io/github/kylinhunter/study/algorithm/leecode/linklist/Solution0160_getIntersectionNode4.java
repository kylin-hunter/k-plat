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
public class Solution0160_getIntersectionNode4 extends Solution0160_getIntersectionNode1 {
    /**
     * @param headA headA
     * @param headB headB
     * @return com.kylinhunter.plat.algorithm.study.leecode.linklist.ListNode
     * @title getIntersectionNode
     * @description 计算长度，长的前面肯定不相交，跳过即可
     * @author BiJi'an
     * @date 2022-08-01 01:04
     */
    @Override
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        if (headA == null || headB == null) {
            return null;
        }

        int len1 = 0;
        ListNode l1 = headA;
        while (l1 != null) {
            len1++;
            l1 = l1.next;
        }

        int len2 = 0;
        ListNode l2 = headB;
        while (l2 != null) {
            len2++;
            l2 = l2.next;
        }

        l1 = headA;
        l2 = headB;
        if (len1 > len2) {

            for (int i = 0; i < len1 - len2; i++) {
                l1 = l1.next;
            }
        } else {
            for (int i = 0; i < len2 - len1; i++) {
                l2 = l2.next;
            }
        }

        while (l1 != null && l2 != null) {
            if (l1 == l2) {
                return l1;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        return null;

    }

}
