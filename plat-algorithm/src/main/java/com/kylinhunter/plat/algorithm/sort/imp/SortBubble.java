package com.kylinhunter.plat.algorithm.sort.imp;

import com.kylinhunter.plat.algorithm.sort.common.AbstractSort;

/**
 * @author BiJi'an
 * @description
 * @date 2022-07-16 23:32
 **/
public class SortBubble extends AbstractSort {
    @Override
    public void sort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
        }

    }

}
