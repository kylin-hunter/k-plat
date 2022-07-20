package com.kylinhunter.plat.algorithm.sort.imp;

import com.kylinhunter.plat.algorithm.sort.common.AbstractSort;

/**
 * 合并排序
 * 大数组切成2个小数组
 * 每个小数组不断地再次切成更小的数组
 * 但数组变成1的时候，终止切分。
 * 然后开始逐级向上合并数组，即合并left->right这一段小数组，即merge方法。
 * merge的时候，会依赖一个临时数组
 * 临时数组里存储了合并后的排序。
 * 临时存储存储到原来的数组中left->right这个数组片段中去。
 *
 * @author BiJi'an
 * @description
 * @date 2022-07-16 23:32
 **/
public class SortMerge extends AbstractSort {
    @Override
    public void doSort(int[] arr) {
        int[] tmp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, tmp);
    }

    public void mergeSort(int[] arr, int left, int right, int[] tmp) {

        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, tmp);
            mergeSort(arr, mid + 1, right, tmp);
            merge(arr, left, mid, right, tmp);
        }
    }

    private void merge(int[] arr, int left, int mid, int right, int[] tmp) {
        //        System.out.println("merge left:" + Arrays.toString(Arrays.copyOfRange(arr, left, mid + 1)));
        //        System.out.println("merge right:" + Arrays.toString(Arrays.copyOfRange(arr, mid + 1, right + 1)));

        int t = 0;
        int i = left;
        int j = mid + 1;
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                tmp[t++] = arr[i++];
            } else {
                tmp[t++] = arr[j++];
            }
        }

        while (i <= mid) {
            tmp[t++] = arr[i++];
        }
        while (j <= right) {
            tmp[t++] = arr[j++];
        }

        t = 0;
        int dist = left;
        while (dist <= right) {
            arr[dist++] = tmp[t++];
        }
        //        System.out.println("merge:" + Arrays.toString(Arrays.copyOf(tmp, right - left + 1)));
    }

}
