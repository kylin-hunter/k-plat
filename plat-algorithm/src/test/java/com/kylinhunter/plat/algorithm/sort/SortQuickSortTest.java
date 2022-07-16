package com.kylinhunter.plat.algorithm.sort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.kylinhunter.plat.algorithm.sort.data.SortSimpleData;
import com.kylinhunter.plat.commons.service.EServices;

class SortQuickSortTest {
    private static Sort sort;

    @BeforeAll
    static void beforeAll() {

        sort = EServices.get(SortType.QuickSort);
    }

    @Test
    public void sort() {

        int[] arr = SortSimpleData.getUnSorted();
        sort.sort(arr);
        Assertions.assertArrayEquals(arr, SortSimpleData.SORTED);
    }

}