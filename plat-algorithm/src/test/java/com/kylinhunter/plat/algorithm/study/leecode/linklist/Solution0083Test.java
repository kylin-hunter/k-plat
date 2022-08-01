package com.kylinhunter.plat.algorithm.study.leecode.linklist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.kylinhunter.plat.algorithm.study.leecode.linklist.common.ListOperator;

public class Solution0083Test {
    int[] testArr1 = new int[] {1, 1, 2};
    int[] expectRet1 = new int[] {1, 2};
    int[] testArr2 = new int[] {1, 1, 2, 3, 3};
    int[] expectRet2 = new int[] {1, 2, 3};

    public void test1(Solution0083_deleteDuplicates1 deleteDuplicates1) {
        ListNode testList1 = ListOperator.init(testArr1);
        ListNode expectResult1 = ListOperator.init(expectRet1);
        ListNode result1 = deleteDuplicates1.deleteDuplicates(testList1);
        ListOperator.print(testList1);
        ListOperator.print(result1);
        Assertions.assertTrue(ListOperator.equals(expectResult1, result1));

    }

    public void test2(Solution0083_deleteDuplicates1 deleteDuplicates1) {

        ListNode testList2 = ListOperator.init(testArr2);
        ListNode expectResult2 = ListOperator.init(expectRet2);
        ListNode result2 = deleteDuplicates1.deleteDuplicates(testList2);
        ListOperator.print(testList2);
        ListOperator.print(result2);
        Assertions.assertTrue(ListOperator.equals(expectResult2, result2));

    }

    @Test
    public void test1() {

        test1(new Solution0083_deleteDuplicates1());
        test2(new Solution0083_deleteDuplicates1());

    }

    @Test
    public void test2() {

        test1(new Solution0083_deleteDuplicates2());
        test2(new Solution0083_deleteDuplicates2());

    }

}