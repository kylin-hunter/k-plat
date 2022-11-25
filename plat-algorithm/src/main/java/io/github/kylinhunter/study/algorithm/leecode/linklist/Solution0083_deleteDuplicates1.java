package io.github.kylinhunter.study.algorithm.leecode.linklist;

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
public class Solution0083_deleteDuplicates1 {

    /**
     * @param head head
     * @return com.kylinhunter.plat.algorithm.study.leecode.linklist.ListNode
     * @title deleteDuplicates
     * @description 采用常规遍历，相邻值去掉
     * @author BiJi'an
     * @date 2022-07-31 23:37
     */
    public ListNode deleteDuplicates(ListNode head) {

        if (head == null) {
            return null;
        }
        ListNode node = head;

        while (node.next != null) {
            if (node.val == node.next.val) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }

        }

        return head;
    }

}
