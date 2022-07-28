package com.kylinhunter.plat.algorithm.study.leecode.basic;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Solution0053Test {
    private final int numsOri1[] = new int[] {0, 1, 2, 3, 5, 6, 7, 8};
    private final int expect1 = 4;

    private final int numsOri2[] = new int[] {0, 2, 3, 4, 5, 6, 7, 8};
    private final int expect2 = 1;

    private final int numsOri3[] = new int[] {0, 1, 2, 3, 4, 5, 6, 8};
    private final int expect3 = 7;

    @Test
    public void test1() {
        Solution0053_MissNumber1 missNumber = new Solution0053_MissNumber1();
        Assertions.assertEquals(expect1, missNumber.missNumber(Arrays.copyOf(numsOri1, numsOri1.length)));
        Assertions.assertEquals(expect2, missNumber.missNumber(Arrays.copyOf(numsOri2, numsOri2.length)));
        Assertions.assertEquals(expect3, missNumber.missNumber(Arrays.copyOf(numsOri3, numsOri3.length)));
    }

    @Test
    public void test2() {
        Solution0053_MissNumber2 missNumber = new Solution0053_MissNumber2();
        Assertions.assertEquals(expect1, missNumber.missNumber(Arrays.copyOf(numsOri1, numsOri1.length)));
        Assertions.assertEquals(expect2, missNumber.missNumber(Arrays.copyOf(numsOri2, numsOri2.length)));
        Assertions.assertEquals(expect3, missNumber.missNumber(Arrays.copyOf(numsOri3, numsOri3.length)));
    }

}