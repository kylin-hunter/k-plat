package com.kylinhunter.plat.algorithm.leecode;

/**
 * 题目 ：两数之和
 * 给定一个整数数组 nums和一个整数目标值 target，
 * 请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * <p>
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 *
 * @author BiJi'an
 * @description
 * @date 2022-07-25 16:48
 **/
public class Solution0001_TwoNum1 {

    /**
     * @param nums
     * @param target
     * @return int[]
     * @throws
     * @title twoSum
     * @description 暴力求解
     * @author BiJi'an
     * @date 2022-07-25 19:25
     */
    public int[] twoSum(int[] nums, int target) {
        int r1 = 0, r2 = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[] {r1, r2};
    }

}
