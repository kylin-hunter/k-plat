package io.github.kylinhunter.study.algorithm.leecode.basic;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Solution0283Test {
    private final int numsOri[] = new int[] {0, 1, 0, 3, 12};
    private final int expect[] = new int[] {1, 3, 12, 0, 0};

    @Test
    public void test1() {
        int[] nums = Arrays.copyOf(numsOri, numsOri.length);
        Solution0283_MoveZeros1 moveZeros1 = new Solution0283_MoveZeros1();
        moveZeros1.moveZeroes(nums);
        Assertions.assertArrayEquals(expect, nums);
    }

    @Test
    public void test2() {

    }

}