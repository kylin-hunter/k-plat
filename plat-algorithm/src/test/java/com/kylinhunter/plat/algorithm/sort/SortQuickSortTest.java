package com.kylinhunter.plat.algorithm.sort;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.kylinhunter.plat.commons.service.EServices;

class SortQuickSortTest extends SortCommonTest {
    private static Sort sort;

    @BeforeAll
    static void beforeAll() {

        sort = EServices.get(SortType.QuickSort);
    }

    @Test
    public void sort() {

        this.sort(sort);
    }
}