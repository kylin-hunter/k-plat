package com.kylinhunter.plat.algorithm.sort.imp;

import com.kylinhunter.plat.algorithm.sort.common.AbstractSort;

/**
 * 插入排序
 *
 * @author BiJi'an
 * @description
 * @date 2022-07-16 23:32
 **/
public class SortInsertion extends AbstractSort {
    @Override
    public void doSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                swap(arr, j, j - 1);
            }
        }
    }

}
