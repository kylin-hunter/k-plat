package io.github.kylinhunter.study.algorithm.leecode.linklist;

import io.github.kylinhunter.study.algorithm.leecode.linklist.common.ListOperator;

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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {

            list2.next = mergeTwoLists(list1, list2.next);
            return list2;

        }

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
