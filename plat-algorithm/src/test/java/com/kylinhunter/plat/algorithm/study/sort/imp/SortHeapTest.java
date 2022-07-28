package com.kylinhunter.plat.algorithm.study.sort.imp;

import org.junit.jupiter.api.Test;

import com.kylinhunter.plat.algorithm.study.sort.SortType;

class SortHeapTest extends AbstractSortTest {
    @Test
    public void sort() {
        this.sort(SortType.Heap);
    }
}