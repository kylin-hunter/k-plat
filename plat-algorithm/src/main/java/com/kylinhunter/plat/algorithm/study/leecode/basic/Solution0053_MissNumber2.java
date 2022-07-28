package com.kylinhunter.plat.algorithm.study.leecode.basic;

/**
 * 题目 ：找到所有数组中消失的数字
 * 0～n-1中缺失的数字。
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，
 * 并且每个数字都在范围0～n-1之内。
 * 在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 * 比如 []，这个数组长度为9-1，n为9，
 * 每个数字都在0~8之间，找到数组中缺失的4。
 *
 * @author BiJi'an
 * @description
 * @date 2022-07-25 16:48
 **/
public class Solution0053_MissNumber2 {

    public int missNumber(int[] nums) {

        return missNumber(nums, 0, nums.length - 1);

    }

    /**
     * @param nums  nums
     * @param start start
     * @param end   end
     * @return int
     * @title missNumber
     * @description =>
     * 采用二分查找的思想
     * 1、先找中间的值
     * 2、如果i==nums[i]，肯定要找右侧半个数组
     * 3、如果i!nums[i],则这个i可能是要找的值
     * 4、如果i的前一个值 j 是j==nums[j],则这个i就是要找的值
     * 5、反之，即上面的j!=nums[j]，说明i不是要找的值，需要统一在做半个数组中去找。
     * @author BiJi'an
     * @date 2022-07-26 15:01
     */
    public int missNumber(int[] nums, int start, int end) {
        //        System.out.println(start + ":" + end);
        if (end >= start) {
            int l = start + (end - start) / 2;
            if (l == nums[l]) { // 找右侧
                return missNumber(nums, l + 1, end);
            } else {
                if (l - 1 == nums[l - 1]) {
                    return l;
                } else { // 找左侧
                    return missNumber(nums, start, l - 1);
                }
            }
        } else {
            return -1;
        }

    }
}
