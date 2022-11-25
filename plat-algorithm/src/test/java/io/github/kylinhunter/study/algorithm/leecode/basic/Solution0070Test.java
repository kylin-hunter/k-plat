package io.github.kylinhunter.study.algorithm.leecode.basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Solution0070Test {
    private final int n = 15;
    private final int expectResult = 987;

    @Test
    public void climbStairs1() {

        int result = new Solution0070_ClimbStairs1().climbStairs(n);
        Assertions.assertEquals(expectResult, result);
        System.out.println("result:" + result);
    }

    @Test
    public void climbStairs2() {
        int result = new Solution0070_ClimbStairs2().climbStairs(n);
        Assertions.assertEquals(expectResult, result);
        System.out.println("result:" + result);
    }

    @Test
    public void climbStair3() {

        int result = new Solution0070_ClimbStairs3().climbStairs(n);
        Assertions.assertEquals(expectResult, result);
        System.out.println("result:" + result);
    }

}