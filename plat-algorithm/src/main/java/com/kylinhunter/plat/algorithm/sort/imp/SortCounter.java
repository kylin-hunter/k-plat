package com.kylinhunter.plat.algorithm.sort.imp;

import java.util.Arrays;

import com.kylinhunter.plat.algorithm.sort.common.AbstractSort;

/**
 * 计数排序 空间换时间
 *
 * @author BiJi'an
 * @description
 * @date 2022-07-16 23:32
 **/
public class SortCounter extends AbstractSort {

    @Override
    public void doSort(int[] arr) {

        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }

        int[] countArr = new int[max - min + 1];

        for (int i = 0; i < arr.length; i++) {
            countArr[arr[i] - min]++;
        }
        System.out.println(String.format("%-20s  %s", "countArr:", Arrays.toString(countArr)));

        int index = 0;
        for (int i = 0; i < countArr.length; i++) {
            while (countArr[i] > 0) {
                countArr[i]--;
                arr[index++] = i + min;
            }
        }
    }
}
