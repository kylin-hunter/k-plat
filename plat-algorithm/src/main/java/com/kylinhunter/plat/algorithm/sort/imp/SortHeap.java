package com.kylinhunter.plat.algorithm.sort.imp;

import java.util.Arrays;

import com.kylinhunter.plat.algorithm.sort.common.AbstractSort;

/**
 * 堆排序
 *
 * @author BiJi'an
 * @description
 * @date 2022-07-16 23:32
 **/
public class SortHeap extends AbstractSort {

    int RADIX = 10;

    @Override
    public void doSort(int[] arr) {

        buildMaxHeap(arr);
        int len = arr.length;

        while (len > 0) {
            swap(arr, 0, len - 1);
            len--;
            ajust(arr, 0, len);
            System.out.println("--------------------" + Arrays.toString(arr));

        }

    }

    private void buildMaxHeap(int[] arr) {
        int len = arr.length;
        for (int i = (int) Math.floor(len / 2); i >= 0; i--) {
            System.out.println("adjust1 :" + i + ":" + len);

            ajust(arr, i, len);
        }

        System.out.println("构造完成最大堆" + Arrays.toString(arr));

    }

    private void ajust(int[] arr, int i, int len) {
        System.out.println("adjust==>:" + i + ":" + len);
        System.out.println("22" + Arrays.toString(arr));

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
