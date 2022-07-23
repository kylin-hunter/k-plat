package com.kylinhunter.plat.algorithm.sort.imp;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;

import com.kylinhunter.plat.algorithm.sort.Sort;
import com.kylinhunter.plat.algorithm.sort.SortType;
import com.kylinhunter.plat.algorithm.sort.data.SortData;
import com.kylinhunter.plat.algorithm.sort.data.SortDataGenerator;
import com.kylinhunter.plat.commons.service.EServices;

/**
 * @author BiJi'an
 * @description
 * @date 2022-07-18 01:30
 **/
public abstract class AbstractSortTest {

    public void sort(SortType sortType) {

        Sort sort = EServices.get(sortType);

        final SortData sortData = SortDataGenerator.get();
        final int[] data = sortData.getData();
        final int[] rightData = sortData.getRightData();

        System.out.println(String.format("%-20s  start", sortType.clazz.getName()));
        System.out.println(String.format("%-20s  %s", "sort_before:", Arrays.toString(data)));
        System.out.println(String.format("%-20s  %s", "expect:", Arrays.toString(rightData)));
        sort.sort(data);
        System.out.println(String.format("%-20s  %s", "sort after:", Arrays.toString(data)));
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
