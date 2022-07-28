package com.kylinhunter.plat.algorithm.study.leecode.basic;

/**
 * 题目 ：合并两个有序数组
 * 给你两个按 非递减顺序 排列的整数数组nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 * <p>
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 * <p>
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 *
 * @author BiJi'an
 * @description
 * @date 2022-07-25 16:48
 **/
public class Solution0088_Merge2SortedArr1 {

    /**
     * @param nums1 nums1
     * @param m     m
     * @param nums2 num2
     * @param n     n
     * @return void
     * @title merge
     * @description 逐一比较大小，比较后的结果放到一个空白数组中
     * @author BiJi'an
     * @date 2022-07-26 00:07
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int[] result = new int[m + n];
        int i = 0;
        int j = 0;
        int index = 0;
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {

                result[index++] = nums1[i++];
            } else {

                result[index++] = nums2[j++];
            }

        }
        while (i < m) {
            result[index++] = nums1[i++];
        }

        while (j < n) {
            result[index++] = nums2[j++];
        }

        for (i = 0; i < result.length; i++) {
            nums1[i] = result[i];
        }
    }

}
