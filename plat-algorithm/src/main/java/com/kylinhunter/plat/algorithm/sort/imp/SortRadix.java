package com.kylinhunter.plat.algorithm.sort.imp;

import com.kylinhunter.plat.algorithm.sort.common.AbstractSort;

/**
 * 基数
 *
 * @author BiJi'an
 * @description
 * @date 2022-07-16 23:32
 **/
public class SortRadix extends AbstractSort {

    int RADIX = 10;

    @Override
    public void doSort(int[] arr) {
        int maxDigit = calMaxDigit(arr);
        int[][] buckets = new int[RADIX][arr.length + 1];// 第一位存储数量

        for (int i = 0; i < maxDigit; i++) {
            for (int j = 0; j < arr.length; j++) {

                int bucketNo = this.getBucketNo(arr[j], i);
                final int[] bucket = buckets[bucketNo];
                bucket[0]++;// 获取最新位置
                bucket[bucket[0]] = arr[j];//存储
            }

            int index = 0;
            for (int k = 0; k < buckets.length; k++) {//收集桶数据
                int[] bucket = buckets[k];
                int len = bucket[0];
                for (int m = 1; m <= len; m++) {

                    arr[index++] = bucket[m];
                }

                bucket[0] = 0;//重置数量 方便下次排序

            }
        }

    }

    private int getBucketNo(int data, int postion) {
        if (postion > 0) {
            data = data / (RADIX * postion);
        }
        return data % RADIX;

    }

    private int calMaxDigit(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        int maxDigit = 1;
        while (max != 0) {
            maxDigit++;
            max = max / 10;
        }
        return maxDigit;
    }

}
