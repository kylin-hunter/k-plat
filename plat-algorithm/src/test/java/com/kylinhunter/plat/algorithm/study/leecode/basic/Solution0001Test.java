package com.kylinhunter.plat.algorithm.study.leecode.basic;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Solution0001Test {
    private final int nums1[] = new int[] {1, 2, 7, 11, 15};
    int target1 = 9;
    private final int nums2[] = new int[] {3, 2, 4};
    int target2 = 6;

    private final int nums3[] = new int[] {9,-3, 2, 4};
    int target3 = 1;

    @Test
    public void test1() {

        Solution0001_TwoNum1 twoNum1 = new Solution0001_TwoNum1();
        test(nums1,target1,twoNum1.twoSum(nums1, target1));
        test(nums2,target2,twoNum1.twoSum(nums2, target2));
        test(nums3,target3,twoNum1.twoSum(nums3, target3));
    }

    @Test
    public void test2() {

        Solution0001_TwoNum2 twoNum2 = new Solution0001_TwoNum2();
        test(nums1,target1,twoNum2.twoSum(nums1, target1));
        test(nums2,target2,twoNum2.twoSum(nums2, target2));
        test(nums3,target3,twoNum2.twoSum(nums3, target3));

    }

    @Test
    public void test3() {

        Solution0001_TwoNum3 twoNum3 = new Solution0001_TwoNum3();
        test(nums1,target1,twoNum3.twoSum(nums1, target1));
        test(nums2,target2,twoNum3.twoSum(nums2, target2));
        test(nums3,target3,twoNum3.twoSum(nums3, target3));

    }


    private  void test(int []nums,int target,int[] result){
        System.out.println("result:" + Arrays.toString(result));
        Assertions.assertEquals(target, nums[result[0]], nums[result[1]]);

    }

}