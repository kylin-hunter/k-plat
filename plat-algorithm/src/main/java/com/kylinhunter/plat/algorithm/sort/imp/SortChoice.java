package com.kylinhunter.plat.algorithm.sort.imp;

import com.kylinhunter.plat.algorithm.sort.common.AbstractSort;

/**
 * 选择排序
 * 从i开始遍历。选出最小的值，放入位置i
 *
 * @author BiJi'an
 * @description
 * @date 2022-07-16 23:32
 **/
public class SortChoice extends AbstractSort {
    @Override
    public void doSort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

}
