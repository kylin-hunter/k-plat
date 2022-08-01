package com.kylinhunter.plat.algorithm.study.leecode.linklist;

/**
 * 题目 ：删除重复原始
 * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
 * <p>
 * 输入：head = [1,1,2]
 * 输出：[1,2]
 * <p>
 * 输入：head = [1,1,2,3,3]
 * 输出：[1,2,3]
 *
 * @author BiJi'an
 * @description
 * @date 2022-07-25 16:48
 **/
public class Solution0083_deleteDuplicates2 extends Solution0083_deleteDuplicates1 {
    /**
     * @param head head
     * @return com.kylinhunter.plat.algorithm.study.leecode.linklist.ListNode
     * @title deleteDuplicates
     * @description 递归求解
     * @author BiJi'an
     * @date 2022-07-31 23:37
     */
    @Override
    public ListNode deleteDuplicates(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        head.next = deleteDuplicates(head.next);

        return head.val != head.next.val ? head : head.next;

    }

}
