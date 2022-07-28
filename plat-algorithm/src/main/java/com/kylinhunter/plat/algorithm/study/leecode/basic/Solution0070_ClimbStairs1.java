package com.kylinhunter.plat.algorithm.study.leecode.basic;

/**
 * 题目：爬楼梯
 * <p>
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * @author BiJi'an
 * @description
 * @date 2022-07-25 16:48
 **/
public class Solution0070_ClimbStairs1 {
    /**
     * @param n 台阶数
     * @return int
     * @title climbStairs
     * @description 截图思路
     * 第一和第二级 爬楼梯的方法是固定的 1和2
     * 第三级是第一+第二级 的和
     * 整体是个递归
     * @author BiJi'an
     * @date 2022-07-25 18:23
     */
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            return climbStairs(n - 1) + climbStairs(n - 2);
        }
    }

}
