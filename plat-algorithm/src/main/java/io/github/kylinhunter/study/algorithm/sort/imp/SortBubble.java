package io.github.kylinhunter.study.algorithm.sort.imp;

import io.github.kylinhunter.study.algorithm.sort.common.AbstractSort;

import io.github.kylinhunter.commons.component.C;

/**
 * 冒泡排序
 * 基础排序，双层循环，遍历 两两比较
 *
 * @author BiJi'an
 * @description
 * @date 2022-07-16 23:32
 **/
@C
public class SortBubble extends AbstractSort {
    @Override
    public void doSort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
        }

    }

}
