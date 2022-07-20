package com.kylinhunter.plat.algorithm.sort.data;

import java.util.Arrays;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-07-18 03:06
 **/
@RequiredArgsConstructor
@Getter
public class SortData {
    private final int[] data;
    private final int[] rightData;

    public int[] getData() {
        return Arrays.copyOf(data, data.length);
    }
}
