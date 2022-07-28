package com.kylinhunter.plat.algorithm.study.leecode.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目 ：找到所有数组中消失的数字
 * <p>
 * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。
 * 请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
 * 示例 1：
 * 输入：nums = [4,3,2,7,8,2,3,1]
 * 输出：[5,6]
 * 示例 2：
 * <p>
 * 输入：nums = [1,1]
 * 输出：[2]
 *
 * @author BiJi'an
 * @description
 * @date 2022-07-25 16:48
 **/
public class Solution0448_DisappearedNumber2 {
    /**
     * @param nums nums
     * @return java.util.List<java.lang.Integer>
     * @title findDisappearedNumbers
     * @description 里面负数求解
     * @author BiJi'an
     * @date 2022-07-26 01:56
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {

        List<Integer> result = new ArrayList<>();

        int index = 0;
        for (int i = 0; i < nums.length; i++) {

            index = nums[i];
            if (index < 0) {
                index = -index;
            }

            if (nums[index - 1] > 0) {
                nums[index - 1] = -nums[index - 1];
            }

        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }

        return result;

    }
}
