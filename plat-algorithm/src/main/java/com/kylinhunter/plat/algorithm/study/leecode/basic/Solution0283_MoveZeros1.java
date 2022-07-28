package com.kylinhunter.plat.algorithm.study.leecode.basic;

/**
 * 题目 ：移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 * <p>
 * 示例1：
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 示例 2:
 * <p>
 * 输入: nums = [0]
 * 输出: [0]
 *
 * @author BiJi'an
 * @description
 * @date 2022-07-25 16:48
 **/
public class Solution0283_MoveZeros1 {

    /**
     * @param nums nums
     * @return void
     * @title moveZeroes
     * @description
     * @author BiJi'an
     * @date 2022-07-26 01:03
     */
    public void moveZeroes(int[] nums) {

        int j = 0;
        for (int i = 0; i < nums.length; i++) {

            if (nums[i] != 0) {
                nums[j++] = nums[i];

            }
        }
        for (; j < nums.length; j++) {
            nums[j] = 0;
        }

    }

}
