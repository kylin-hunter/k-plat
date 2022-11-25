package io.github.kylinhunter.study.algorithm.sort.imp;

import org.junit.jupiter.api.Test;

import io.github.kylinhunter.study.algorithm.sort.SortType;



public class SortHeapTest extends AbstractSortTest {
    @Test
    public void sort() {
        this.sort(SortType.Heap.clazz);
    }
}