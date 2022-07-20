package com.kylinhunter.plat.algorithm.sort.imp;

import com.kylinhunter.plat.algorithm.sort.common.AbstractSort;

/**
 * 插入排序
 * 第一步：从第2个数字开始遍历，比第一个值小，就交换
 * 第二步：从第3个数字开始遍历，和前面的两个值比较，小就交换，发现第一个不小的就终止。
 * 第三步：继续，直至结束
 *
 * @author BiJi'an
 * @description
 * @date 2022-07-16 23:32
 **/
public class SortInsertion extends AbstractSort {
    @Override
    public void doSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

}
