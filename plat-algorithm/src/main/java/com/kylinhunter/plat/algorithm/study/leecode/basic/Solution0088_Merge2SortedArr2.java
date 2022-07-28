package com.kylinhunter.plat.algorithm.study.leecode.basic;

/**
 * 题目 ：参见  Solution0088_Merge2SortedArr1.java
 *
 * @author BiJi'an
 * @description
 * @date 2022-07-25 16:48
 **/
public class Solution0088_Merge2SortedArr2 {

    /**
     * @param nums1 nums1
     * @param m     m
     * @param nums2 nums2
     * @param n     n
     * @return void
     * @title merge
     * @description 利用nums1中的空白位置求解
     * @author BiJi'an
     * @date 2022-07-26 00:59
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int i = m - 1;
        int j = n - 1;
        int index = m + n - 1;
        while (j >= 0 && i >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[index--] = nums1[i--];
            } else {
                nums1[index--] = nums2[j--];
            }
        }

        while (i >= 0) {
            nums1[index--] = nums1[i--];
        }

        while (j >= 0) {
            nums1[index--] = nums2[j--];
        }

    }

}
