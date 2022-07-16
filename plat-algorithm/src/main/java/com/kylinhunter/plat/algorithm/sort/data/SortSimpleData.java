package com.kylinhunter.plat.algorithm.sort.data;

import java.util.Arrays;

/**
 * @author BiJi'an
 * @description
 * @date 2022-07-16 23:34
 **/
public class SortSimpleData {
    private final static int[] UN_SORTED = {86, 11, 77, 23, 32, 45, 58, 63, 93, 4, 37, 22, 32};
    public final static int[] SORTED = {4, 11, 22, 23, 32, 32, 37, 45, 58, 63, 77, 86, 93};

    public static int[] getUnSorted() {
        return Arrays.copyOf(UN_SORTED, UN_SORTED.length);
    }

}
