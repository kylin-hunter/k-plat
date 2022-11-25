package io.github.kylinhunter.study.algorithm.leecode.basic;

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
public class Solution0509_Fibonacci1 {

    /**
     * @param n
     * @return int
     * @throws
     * @title fib
     * @description 解题思路：递归
     * @author BiJi'an
     * @date 2022-07-25 19:09
     */
    public int fib(int n) {

        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

}