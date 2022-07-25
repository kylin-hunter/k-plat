package com.kylinhunter.plat.algorithm.leecode;

/**
 * 题目：斐波那契数列
 * 斐波那契数（通常用F(n) 表示）形成的序列称为 斐波那契数列 。
 * 该数列由0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 * F(0) = 0，F(1)= 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给定n ，请计算 F(n) 。
 *
 * @author BiJi'an
 * @description
 * @date 2022-07-25 16:48
 **/
public class Solution0509_Fibonacci2 {

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