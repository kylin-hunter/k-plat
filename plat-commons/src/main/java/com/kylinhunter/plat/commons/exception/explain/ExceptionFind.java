package com.kylinhunter.plat.commons.exception.explain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 01:44
 **/
@AllArgsConstructor
@Getter
public class ExceptionFind {
    private final Throwable target;
    private final Class<? extends Throwable> source;

}