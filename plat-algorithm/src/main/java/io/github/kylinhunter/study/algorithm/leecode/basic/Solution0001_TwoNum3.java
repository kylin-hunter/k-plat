package io.github.kylinhunter.study.algorithm.leecode.basic;

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
public class Solution0001_TwoNum3 {

    /**
     * @param nums   nums
     * @param target target
     * @return int[]
     * @title twoSum
     * @description 空间换时间求解, 有可能超出内存限制，并不推荐
     * @author BiJi'an
     * @date 2022-07-25 19:25
     */
    public int[] twoSum(int[] nums, int target) {
        int r1 = 0, r2 = 0;

        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
            if (nums[i] < min) {
                min = nums[i];
            }
        }
        int[] arrTable = new int[max - min + 1];

        for (int i = 0; i < nums.length; i++) {
            arrTable[nums[i] - min] = i + 1;
        }
        for (int i = 0; i < nums.length; i++) {
            int otherNumLoc = target - nums[i] - min;
            if (otherNumLoc >= 0 && otherNumLoc < arrTable.length) {
                int index = arrTable[otherNumLoc];
                if (index > 0) {
                    if (index - 1 != i) {
                        return new int[] {i, index - 1};
                    }
                }
            }

        }
        return new int[] {r1, r2};
    }

}
