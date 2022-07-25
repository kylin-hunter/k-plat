package com.kylinhunter.plat.algorithm.leecode;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Solution0088Test {
    private final int nums1Ori[] = new int[] {1, 2, 3, 0, 0, 0};

    private final int nums2Ori[] = new int[] {2, 5, 6};

    private final int expect[] = new int[] {1, 2, 2, 3, 5, 6};

    @Test
    public void test1() {

        int[] nums1 = Arrays.copyOf(nums1Ori, nums1Ori.length);
        int[] nums2 = Arrays.copyOf(nums2Ori, nums2Ori.length);
        Solution0088_Merge2SortedArr1 merge2SortedArr1 = new Solution0088_Merge2SortedArr1();
        merge2SortedArr1.merge(nums1, 3, nums2, 3);
        Assertions.assertArrayEquals(expect, nums1);

    }

    @Test
    public void test2() {

        int[] nums1 = Arrays.copyOf(nums1Ori, nums1Ori.length);
        int[] nums2 = Arrays.copyOf(nums2Ori, nums2Ori.length);
        Solution0088_Merge2SortedArr2 merge2SortedArr2 = new Solution0088_Merge2SortedArr2();
        merge2SortedArr2.merge(nums1, 3, nums2, 3);
        Assertions.assertArrayEquals(expect, nums1);

    }


}