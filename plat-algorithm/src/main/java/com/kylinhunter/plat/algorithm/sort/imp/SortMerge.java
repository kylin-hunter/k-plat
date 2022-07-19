package com.kylinhunter.plat.algorithm.sort.imp;

import com.kylinhunter.plat.algorithm.sort.common.AbstractSort;

/**
 * 合并排序
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
