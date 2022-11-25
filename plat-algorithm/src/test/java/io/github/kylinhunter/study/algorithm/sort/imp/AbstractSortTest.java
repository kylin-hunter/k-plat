package io.github.kylinhunter.study.algorithm.sort.imp;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;

import io.github.kylinhunter.study.algorithm.sort.Sort;
import io.github.kylinhunter.study.algorithm.sort.data.SortData;
import io.github.kylinhunter.study.algorithm.sort.data.SortDataGenerator;

import io.github.kylinhunter.commons.component.CF;

/**
 * @author BiJi'an
 * @description
 * @date 2022-07-18 01:30
 **/
public abstract class AbstractSortTest {

    public void sort(Class<? extends Sort> sortClass) {

        Sort sort = CF.get(sortClass);

        final SortData sortData = SortDataGenerator.get();
        final int[] data = sortData.getData();
        final int[] rightData = sortData.getRightData();

        System.out.println(String.format("%-20s  start", sortClass.getName()));
        System.out.println(String.format("%-20s  %s", "sort_before:", Arrays.toString(data)));
        System.out.println(String.format("%-20s  %s", "expect:", Arrays.toString(rightData)));
        sort.sort(data);
        System.out.println(String.format("%-20s  %s", "sort after:", Arrays.toString(data)));
        Assertions.assertArrayEquals(rightData, data);
    }

    public void sortBeanchmark(Sort sort) {

        final SortData sortData = SortDataGenerator.get(10000);
        final int[] data = sortData.getData(); // 重写了此方法，每次都拿到一份新的待排序数据
        final int[] rightData = sortData.getRightData();

        //        System.out.println("data:\t" + Arrays.toString(data));
        //        System.out.println("rightData:\t" + Arrays.toString(rightData));
        sort.sort(data);

        //        System.out.println("sort_result:\t" + Arrays.toString(data));
        Assertions.assertArrayEquals(rightData, data);
    }
}
