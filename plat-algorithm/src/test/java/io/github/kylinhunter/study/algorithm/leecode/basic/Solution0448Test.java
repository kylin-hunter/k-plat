package io.github.kylinhunter.study.algorithm.leecode.basic;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Solution0448Test {
    private final int numsOri[] = new int[] {4, 3, 2, 7, 8, 2, 3, 1};
    private final int expect[] = new int[] {5, 6};

    @Test
    public void test1() {
        int[] nums = Arrays.copyOf(numsOri, numsOri.length);
        Solution0448_DisappearedNumber1 disappearedNumber = new Solution0448_DisappearedNumber1();
        Assertions.assertArrayEquals(expect,
                disappearedNumber.findDisappearedNumbers(nums).stream().mapToInt(i -> i).toArray());
    }

    @Test
    public void test2() {
        int[] nums = Arrays.copyOf(numsOri, numsOri.length);
        Solution0448_DisappearedNumber2 disappearedNumber = new Solution0448_DisappearedNumber2();
        Assertions.assertArrayEquals(expect,
                disappearedNumber.findDisappearedNumbers(nums).stream().mapToInt(i -> i).toArray());
    }

}