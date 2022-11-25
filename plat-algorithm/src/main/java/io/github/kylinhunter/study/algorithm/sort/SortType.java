package io.github.kylinhunter.study.algorithm.sort;

import io.github.kylinhunter.study.algorithm.sort.imp.SortBubble;
import io.github.kylinhunter.study.algorithm.sort.imp.SortBucket;
import io.github.kylinhunter.study.algorithm.sort.imp.SortChoice;
import io.github.kylinhunter.study.algorithm.sort.imp.SortCounter;
import io.github.kylinhunter.study.algorithm.sort.imp.SortHeap;
import io.github.kylinhunter.study.algorithm.sort.imp.SortInsertion;
import io.github.kylinhunter.study.algorithm.sort.imp.SortMerge;
import io.github.kylinhunter.study.algorithm.sort.imp.SortQuickSort;
import io.github.kylinhunter.study.algorithm.sort.imp.SortRadix;
import io.github.kylinhunter.study.algorithm.sort.imp.SortShell;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-07-17 00:59
 **/
@Getter
@RequiredArgsConstructor
public enum SortType {
    Bubble(SortBubble.class),
    Insertion(SortInsertion.class),
    Merge(SortMerge.class),
    Choice(SortChoice.class),
    Radix(SortRadix.class),
    Counter(SortCounter.class),
    Heap(SortHeap.class),
    Bucket(SortBucket.class),
    Shell(SortShell.class),
    QuickSort(SortQuickSort.class);
    public final Class<? extends Sort> clazz;
}