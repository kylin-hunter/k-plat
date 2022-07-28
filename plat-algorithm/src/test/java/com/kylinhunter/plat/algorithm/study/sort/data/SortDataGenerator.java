package com.kylinhunter.plat.algorithm.study.sort.data;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

import com.google.common.collect.Maps;
import com.kylinhunter.plat.algorithm.study.sort.imp.SortBubble;

/**
 * @author BiJi'an
 * @description
 * @date 2022-07-16 23:34
 **/
public class SortDataGenerator {
    private static final Random random = new Random();
    private static final int[] UN_SORTED = {86, 11, 77, 23, 32, 45, 58, 63, 93, 4, 37, 22, 32};
    public static final int[] SORTED = {4, 11, 22, 23, 32, 32, 37, 45, 58, 63, 77, 86, 93};

    public static SortData get() {
        return new SortData(UN_SORTED, SORTED);
    }

    private static final Map<Integer, SortData> basicData = Maps.newHashMap();

    public static SortData get(int num) {

        return basicData.computeIfAbsent(num, k -> {
            int[] unsorted = new int[num];
            for (int i = 0; i < num; i++) {
                unsorted[i] = (int) (num * random.nextDouble() + 1);
            }
            int[] sorted = Arrays.copyOf(unsorted, unsorted.length);
            new SortBubble().sort(sorted);
            return new SortData(unsorted, sorted);
        });

    }

}
