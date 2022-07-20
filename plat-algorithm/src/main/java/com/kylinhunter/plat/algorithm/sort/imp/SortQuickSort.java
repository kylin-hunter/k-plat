package com.kylinhunter.plat.algorithm.sort.imp;

import java.util.Random;

import com.kylinhunter.plat.algorithm.sort.common.AbstractSort;

/**
 * 快速排序
 * 递归的思路
 * 第一步：在start\end中间找一个折中点 mid ，mid左侧都比它小，mid右侧都比它大
 * 第二步 mid左侧和mid右侧作为两个新的数组，重复上面的逻辑，不断分裂小数组
 * 直到小数组变为1结束
 * @author BiJi'an
 * @description
 * @date 2022-07-16 23:32
 **/
public class SortQuickSort extends AbstractSort {
    private final Random r = new Random();

    @Override
    public void doSort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private void sort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = partition(arr, start, end);
        if (mid > start) {
            sort(arr, start, mid - 1);
        }
        if (mid < end) {
            sort(arr, mid + 1, end);
        }

    }

    private int partition(int[] arr, int start, int end) {

        int pivot = start + (int) (r.nextDouble() * (end - start + 1));
        swap(arr, pivot, end);/*将基准数和数组尾元素交换位置*/

        int pos = start - 1;
        for (int i = start; i <= end; i++) {

            if (arr[i] <= arr[end]) {
                pos++;
                if (pos < i) {
                    swap(arr, pos, i);
                }

            }
        }
        return pos;

    }

}
