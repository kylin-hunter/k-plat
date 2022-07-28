package com.kylinhunter.plat.algorithm.study.sort.imp;

import com.kylinhunter.plat.algorithm.study.sort.common.AbstractSort;

/**
 * 堆排序
 * 1、构造一个最大堆
 * 2、将堆顶与最后一个值替换
 * 3、从把最后一个值，从堆移除。
 * 4、重新调整堆顶。
 * 5、依此类推，直到堆中只剩一个数，结束
 *
 * @author BiJi'an
 * @description
 * @date 2022-07-16 23:32
 **/
public class SortHeap extends AbstractSort {

    @Override
    public void doSort(int[] arr) {
        buildMaxHeap(arr);
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);
            ajust(arr, 0, i);
        }
    }

    private void buildMaxHeap(int[] arr) {
        int len = arr.length;
        for (int i = len / 2 - 1; i >= 0; i--) {
            ajust(arr, i, len);
        }

    }

    private void ajust(int[] arr, int i, int len) {

        int maxIndex = i;

        int left = 2 * i + 1;
        int right = 2 * (i + 1);

        if (left < len && arr[left] > arr[maxIndex]) {
            maxIndex = left;
        }

        if (right < len && arr[right] > arr[maxIndex]) {
            maxIndex = right;
        }

        if (maxIndex != i) {
            swap(arr, i, maxIndex);
            ajust(arr, maxIndex, len);
        }
    }

}
