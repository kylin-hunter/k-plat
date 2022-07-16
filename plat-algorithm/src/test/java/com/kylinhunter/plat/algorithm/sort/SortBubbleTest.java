package com.kylinhunter.plat.algorithm.sort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.kylinhunter.plat.algorithm.sort.data.SortSimpleData;
import com.kylinhunter.plat.commons.service.EServices;

public class SortBubbleTest {

    private static Sort sort;

    @BeforeAll
    static void beforeAll() {
         EServices.get(SortType.Bubble);
    }

    @Test
    public void sort() {

        int[] arr = SortSimpleData.getUnSorted();
        sort.sort(arr);
        Assertions.assertArrayEquals(arr, SortSimpleData.SORTED);
    }

}