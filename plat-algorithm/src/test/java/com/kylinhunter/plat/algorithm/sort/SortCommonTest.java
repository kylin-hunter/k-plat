package com.kylinhunter.plat.algorithm.sort;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;

import com.kylinhunter.plat.algorithm.sort.data.SortData;
import com.kylinhunter.plat.algorithm.sort.data.SortDataGenerator;

/**
 * @author BiJi'an
 * @description
 * @date 2022-07-18 01:30
 **/
public class SortCommonTest {

    public void sort(Sort sort) {

        final SortData sortData = SortDataGenerator.get();
        final int[] data = sortData.getData();
        final int[] rightData = sortData.getRightData();

        System.out.println("data:\t" + Arrays.toString(data));
        System.out.println("rightData:\t" + Arrays.toString(rightData));
        sort.sort(data);

        System.out.println("sort_result:\t" + Arrays.toString(data));
        Assertions.assertArrayEquals(rightData, data);
    }

    public void sortBeanchmark(Sort sort) {

        final SortData sortData = SortDataGenerator.get(10000);
        final int[] data = sortData.getData();
        final int[] rightData = sortData.getRightData();

        //        System.out.println("data:\t" + Arrays.toString(data));
        //        System.out.println("rightData:\t" + Arrays.toString(rightData));
        sort.sort(data);

        //        System.out.println("sort_result:\t" + Arrays.toString(data));
        Assertions.assertArrayEquals(rightData, data);
    }
}
