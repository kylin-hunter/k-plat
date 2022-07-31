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
public class Solution0021_MergeTwoSortedList1 {

    /**
     * @param list1 list1
     * @param list2 list2
     * @return com.kylinhunter.plat.algorithm.study.leecode.linklist.ListNode
     * @title mergeTwoSortedList
     * @description 注意比对两个链表的最小值，解决
     * @author BiJi'an
     * @date 2022-07-26 19:46
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode rootNode = new ListNode(0);
        ListNode preNode = rootNode;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                preNode.next = list1;
                list1 = list1.next;
            } else {
                preNode.next = list2;
                list2 = list2.next;
            }
            preNode = preNode.next;
        }

        if (list1 != null) {
            preNode.next = list1;
        }

        if (list2 != null) {
            preNode.next = list2;
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
