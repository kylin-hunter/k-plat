package com.kylinhunter.plat.algorithm.study.leecode.linklist;

import com.kylinhunter.plat.algorithm.study.leecode.linklist.common.ListOperator;

/**
 * 题目 ：合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * @author BiJi'an
 * @description
 * @date 2022-07-25 16:48
 **/
public class Solution0021_MergeTwoSortedList2 extends Solution0021_MergeTwoSortedList1 {

    @Override
    public ListNode mergeTwoSortedList(ListNode l1, ListNode l2) {

        ListNode rootNode = new ListNode(0);

        if (l1 == null) {
            return l1;
        }
        if (l2 == null) {
            return l2;
        }

        if (l1.val < l2.val) {
            l1.next = mergeTwoSortedList(l1.next, l2);
        } else {

            l2.next = mergeTwoSortedList(l1, l2.next);

        }

        return rootNode.next;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[] {1, 2, 4};
        int[] arr2 = new int[] {1, 3, 4};
        int[] arr3 = new int[] {1, 1, 2, 3, 4, 4};
        ListNode list1 = ListOperator.init(arr1);
        ListOperator.print(list1);
        ListNode list2 = ListOperator.init(arr2);
        ListOperator.print(list2);
        ListNode list3 = ListOperator.init(arr3);
        ListOperator.print(list3);

    }

}
