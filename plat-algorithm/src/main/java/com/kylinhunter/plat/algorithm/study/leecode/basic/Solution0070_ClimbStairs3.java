package com.kylinhunter.plat.algorithm.study.leecode.basic;

/**
 * 题目：参考 Solution0070_ClimbStairs1.java
 *
 * @author BiJi'an
 * @description
 * @date 2022-07-25 16:48
 **/
public class Solution0070_ClimbStairs3 {

    /**
     * @param n 台阶数
     * @return int
     * @title climbStairs
     * @description 截图思路
     * 参考 ClimbStairs1.java
     * 优化点：用两个变量来缓存前两个台阶的计算方法
     * @author BiJi'an
     * @date 2022-07-25 18:23
     */
    public int climbStairs(int n) {
        if (n >= 1) {
            if (n == 1) {
                return 1;
            } else if (n == 2) {
                return 2;
            } else {
                int n_1 = 1;
                int n_2;
                int r = 2;
                for (int i = 3; i <= n; ++i) {
                    n_2 = n_1;
                    n_1 = r;

                    r = n_2 + n_1;
                }
                return r;
            }

        } else {
            return 0;
        }

    }

}
