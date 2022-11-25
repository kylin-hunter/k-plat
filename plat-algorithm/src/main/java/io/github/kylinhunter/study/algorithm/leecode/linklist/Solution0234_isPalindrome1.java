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
public class Solution0234_isPalindrome1 {

    public boolean isPalindrome(ListNode head) {

        ListNode node = head;
        int len = 0;
        while (node != null) {
            len++;
            node = node.next;
        }
        ListNode[] arr = new ListNode[len];
        int i = 0;
        node = head;

        while (node != null) {
            arr[i++] = node;
            node = node.next;
        }

        int j = 0;
        int k = arr.length - 1;

        while (j < k) {

            if (arr[j].val != arr[k].val) {
                break;
            }
            j++;
            k--;

        }
        if (j - 1 == k || j == k) {
            return true;
        }
        return false;
    }

}
