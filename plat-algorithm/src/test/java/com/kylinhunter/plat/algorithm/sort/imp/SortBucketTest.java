package com.kylinhunter.plat.algorithm.sort.imp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.kylinhunter.plat.algorithm.sort.Sort;
import com.kylinhunter.plat.algorithm.sort.SortType;
import com.kylinhunter.plat.commons.service.EServices;

class SortBucketTest extends AbstractCommonSortTest {
    private static Sort sort;

    @BeforeAll
    static void beforeAll() {
        sort = EServices.get(SortType.Bucket);
    }

    @Test
    public void sort() {
        this.sort(sort);
    }
}