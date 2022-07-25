package com.kylinhunter.plat.algorithm.sort.imp;

import com.kylinhunter.plat.algorithm.sort.common.AbstractSort;

/**
 * 希尔排序（ D.L.Shell 于 1959 年提出而得名）
 * 希尔排序又称缩小增量排序
 * 第一步先2组，即：1，3，5，7，9； 2，4，6，8，10
 * 每个组内分别排序（采用插入排序）
 * 第二步再分成4组，即  1，5，9；2，6，10；3，7；4，8；
 * 每个组内分别排序
 * 第三步分词8组，即 1，9；2，10；，3；4；5；6；7；8；
 * 每个组内分别排序
 * 第四步骤分词16组，分不开的就算了。即，1，2，3，4，5，6，7，8，9，10
 * 至此排序结束。
 * 核心思路，就是一直分下去，分完就排序（采用插入排序），直至每一个组都是1，排序结束。
 *
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
