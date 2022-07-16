package com.kylinhunter.plat.algorithm.sort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.kylinhunter.plat.commons.service.EServices;

public class SortBubbleTest {

    @Test
    public void sort() {
        Sort sort = EServices.get(SortType.Bubble);

        int[] arr = SortData.getUnSorted();
        sort.sort(arr);
        Assertions.assertArrayEquals(arr, SortData.SORTED);
    }

}