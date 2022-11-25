package io.github.kylinhunter.study.algorithm.leecode.linklist;

import java.util.HashSet;
import java.util.Set;

/**
 * 题目 ：相交链表
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。
 * 如果两个链表不存在相交节点，返回 null 。
 *
 * @author BiJi'an
 * @description
 * @date 2022-07-25 16:48
 **/
public class Solution0160_getIntersectionNode2 extends Solution0160_getIntersectionNode1 {

    /**
     * @param headA headA
     * @param headB headB
     * @return com.kylinhunter.plat.algorithm.study.leecode.linklist.ListNode
     * @title getIntersectionNode
     * @description 哈希求解 （不太准确）
     * @author BiJi'an
     * @date 2022-08-01 01:04
     */
    @Override
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        Set<ListNode> tmp = new HashSet<>();

        while (headA != null) {
            tmp.add(headA);
            headA = headA.next;
        }

        while (headB != null) {
            if (tmp.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }

}
