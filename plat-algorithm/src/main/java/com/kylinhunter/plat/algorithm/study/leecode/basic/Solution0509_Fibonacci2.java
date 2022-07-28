package com.kylinhunter.plat.algorithm.study.leecode.basic;

/**
 * 题目：参考 Solution0509_Fibonacci1.java
 *
 * @author BiJi'an
 * @description
 * @date 2022-07-25 16:48
 **/
public class Solution0509_Fibonacci2 {

    /**
     * @param n
     * @return int
     * @throws
     * @title fib
     * @description 解题思路：用两个变量记录前面两个值，一次变量解决
     * @author BiJi'an
     * @date 2022-07-25 19:09
     */
    public int fib(int n) {

        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {

            int n_2;
            int n_1 = 0;
            int r = 1;
            for (int i = 2; i <= n; i++) {
                n_2 = n_1;
                n_1 = r;
                r = n_1 + n_2;
            }
            return r;
        }
    }

}
