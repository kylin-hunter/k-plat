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
public class Solution0448_DisappearedNumber1 {
    /**
     * @param nums nums
     * @return java.util.List<java.lang.Integer>
     * @title findDisappearedNumbers
     * @description 通过hash记录出现过的数字，比较low的解法
     * @author BiJi'an
     * @date 2022-07-26 01:56
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {

        List<Integer> result = new ArrayList<>();
        int[] hash = new int[nums.length + 1];

        for (int num : nums) {
            hash[num] = -1;
        }
        for (int i = 1; i < hash.length; i++) {
            if (hash[i] != -1) {
                result.add(i);

            }
        }

        return result;

    }
}
