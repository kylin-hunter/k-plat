package io.github.kylinhunter.study.algorithm.others.bloom.kylin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author BiJi'an
 * @description
 * @date 2022-07-29 01:04
 **/
@RequiredArgsConstructor
@Getter
@ToString
public enum BloomType {
    N_1000000_01(1000000, 0.01, 6, 9585059),
    N_1000000_001(1000000, 0.001, 9, 14377588),
    N_10000000_01(10000000, 0.01, 6, 95850584),
    N_10000000_001(10000000, 0.001, 9, 143775876),
    N_100000000_01(10000000, 0.01, 6, 958505838),
    N_100000000_001(10000000, 0.01, 9, 1437758757);
    private final int n;
    private final double p;
    private final int k;
    private final int m;

}
