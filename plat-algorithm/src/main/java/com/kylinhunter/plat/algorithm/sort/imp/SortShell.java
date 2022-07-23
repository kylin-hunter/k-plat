package com.kylinhunter.plat.algorithm.sort.imp;

import com.kylinhunter.plat.algorithm.sort.common.AbstractSort;

/**
 * 希尔排序（ D.L.Shell 于 1959 年提出而得名）
 * 希尔排序又称缩小增量排序
 *
 * @author BiJi'an
 * @description
 * @date 2022-07-16 23:32
 **/
public class SortShell extends AbstractSort {
    @Override
    public void doSort(int[] arr) {

        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int tmp = arr[i];
                int j;
                for (j = i; j >= gap; j = j - gap) {
                    if (tmp < arr[j - gap]) {
                        arr[j] = arr[j - gap];
                    } else {
                        break;
                    }
                }
                arr[j] = tmp;
            }

        }

    }

}
