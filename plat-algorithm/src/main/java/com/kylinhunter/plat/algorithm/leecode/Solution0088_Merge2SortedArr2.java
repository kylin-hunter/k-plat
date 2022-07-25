package com.kylinhunter.plat.algorithm.leecode;

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
public class Solution0088_Merge2SortedArr2 {

    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int i = m - 1;
        int j = n - 1;
        int index=m+n -1;
        while(j>=0&&i>=0){
            if(nums1[i]>nums2[j]){
                nums1[index--]=nums1[i--];
            }else{
                nums1[index--]=nums2[j--];
            }
        }

        while(i>=0){
            nums1[index--]=nums1[i--];
        }

        while(j>=0){
            nums1[index--]=nums2[j--];
        }

    }

}
