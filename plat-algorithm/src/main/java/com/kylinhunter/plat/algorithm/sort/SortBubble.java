package com.kylinhunter.plat.algorithm.sort;

/**
 * @author BiJi'an
 * @description
 * @date 2022-07-16 23:32
 **/
public class SortBubble implements Sort {
    @Override
    public void sort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
        }

    }

    private void swap(int arr[], int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
