package com.kylinhunter.plat.algorithm.leecode;

import java.util.HashMap;
import java.util.Map;

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
public class Solution0070_ClimbStairs2 {

    /**
     * @param n 台阶数
     * @return int
     * @title climbStairs
     * @description 截图思路
     * 参考 ClimbStairs1.java
     * 优化点：用Map缓存以前计算过的数据
     * @author BiJi'an
     * @date 2022-07-25 18:23
     */
    public int climbStairs(int n) {
        Map<Integer, Integer> result = new HashMap<>();
        return climbStairs(n, result);
    }

    private int climbStairs(int n, Map<Integer, Integer> result) {

        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {

            Integer n_1 = result.get(n - 1);
            if (n_1 == null) {
                n_1 = climbStairs(n - 1, result);
                result.put(n - 1, n_1);
            }
            Integer n_2 = result.get(n - 2);
            if (n_2 == null) {
                n_2 = climbStairs(n - 2, result);
                result.put(n - 2, n_2);
            }

            return n_1 + n_2;
        }
    }

}
