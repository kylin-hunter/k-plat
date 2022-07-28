package com.kylinhunter.plat.algorithm.study.sort.imp;

import java.util.stream.IntStream;

import com.kylinhunter.plat.algorithm.study.sort.common.AbstractSort;

/**
 * 选择排序
 * 从i开始遍历。选出最小的值，放入位置i
 *
 * @author BiJi'an
 * @description
 * @date 2022-07-16 23:32
 **/
public class SortBucket extends AbstractSort {
    @Override
    public void doSort(int[] arr) {

        int max = IntStream.of(arr).max().getAsInt();

        int[] bucket = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i]]++;
        }

        int index = 0;
        for (int i = 0; i < bucket.length; i++) {

            for (int j = 0; j < bucket[i]; j++) {
                arr[index++] = i;
            }

        }
    }

}
