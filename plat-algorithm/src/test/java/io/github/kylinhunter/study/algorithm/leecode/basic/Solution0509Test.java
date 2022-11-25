package io.github.kylinhunter.study.algorithm.leecode.basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Solution0509Test {
    private final int n = 20;
    private final int expectResult = 6765;

    @Test
    public void test1() {
        int result = new Solution0509_Fibonacci1().fib(n);
        Assertions.assertEquals(expectResult, result);
        System.out.println("result:" + result);
    }

    @Test
    public void test2() {
        int result = new Solution0509_Fibonacci2().fib(n);
        Assertions.assertEquals(expectResult, result);
        System.out.println("result:" + result);
    }

}