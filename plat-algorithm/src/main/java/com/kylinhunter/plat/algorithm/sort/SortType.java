package com.kylinhunter.plat.algorithm.sort;

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
public enum SortType implements EService {
    Bubble(SortBubble.class);
    public final Class<?> clazz;
}