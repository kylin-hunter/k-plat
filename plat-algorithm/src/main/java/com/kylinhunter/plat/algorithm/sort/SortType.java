package com.kylinhunter.plat.algorithm.sort;

import com.kylinhunter.plat.algorithm.sort.imp.SortBubble;
import com.kylinhunter.plat.algorithm.sort.imp.SortChoice;
import com.kylinhunter.plat.algorithm.sort.imp.SortInsertion;
import com.kylinhunter.plat.algorithm.sort.imp.SortMerge;
import com.kylinhunter.plat.algorithm.sort.imp.SortQuickSort;
import com.kylinhunter.plat.commons.service.EService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-07-17 00:59
 **/
@Getter
@RequiredArgsConstructor
public enum SortType implements EService<Sort> {
    Bubble(SortBubble.class),
    Insertion(SortInsertion.class),
    Merge(SortMerge.class),
    Choice(SortChoice.class),
    QuickSort(SortQuickSort.class);
    public final Class<? extends Sort> clazz;
}