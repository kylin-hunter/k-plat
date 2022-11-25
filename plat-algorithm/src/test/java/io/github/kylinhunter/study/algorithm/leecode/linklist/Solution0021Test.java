package io.github.kylinhunter.study.algorithm.leecode.linklist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.kylinhunter.study.algorithm.leecode.linklist.common.ListOperator;

public class Solution0021Test {
    int[] arr1 = new int[] {1, 2, 4};
    int[] arr2 = new int[] {1, 3, 4};
    int[] arr3 = new int[] {1, 1, 2, 3, 4, 4};


    public void test(Solution0021_MergeTwoSortedList1 mergeTwoSortedList1) {
        ListNode list1 = ListOperator.init(arr1);
        ListNode list2 = ListOperator.init(arr2);
        ListNode list3 = ListOperator.init(arr3);
        ListNode result = mergeTwoSortedList1.mergeTwoLists(list1, list2);

        ListOperator.print(list3);
        ListOperator.print(result);

        Assertions.assertTrue(ListOperator.equals(list3, result));
    }

    @Test
    public void test1() {


        test(new Solution0021_MergeTwoSortedList1());

    }

    @Test
    public void test2() {


        test(new Solution0021_MergeTwoSortedList2());

    }





}