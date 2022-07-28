package com.kylinhunter.plat.algorithm.study.sort.common;

import com.kylinhunter.plat.algorithm.study.sort.Sort;

/**
 * @author BiJi'an
 * @description
 * @date 2022-07-16 23:32
 **/
public abstract class AbstractSort implements Sort {

    @Override
    public void sort(int[] arr) {
        if (arr != null && arr.length > 1) {
            doSort(arr);
        }
    }

    protected void swap(int arr[], int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public abstract void doSort(int[] arr);

}
