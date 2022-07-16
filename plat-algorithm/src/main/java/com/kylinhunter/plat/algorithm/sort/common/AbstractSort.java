package com.kylinhunter.plat.algorithm.sort.common;

import com.kylinhunter.plat.algorithm.sort.Sort;

/**
 * @author BiJi'an
 * @description
 * @date 2022-07-16 23:32
 **/
public abstract class AbstractSort implements Sort {

    protected void swap(int arr[], int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
