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
public class Solution0053_MissNumber1 {

    /**
     * @param nums nums
     * @return int
     * @title missNumber
     * @description :
     * 从左向右遍历
     * 第一个下标i不等于num[i]的就是要找的值
     * @author BiJi'an
     * @date 2022-07-26 15:00
     */
    public int missNumber(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i]) {
                return i;
            }
        }

        return -1;

    }
}
