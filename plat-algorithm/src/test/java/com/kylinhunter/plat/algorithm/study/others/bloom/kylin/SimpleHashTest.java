package com.kylinhunter.plat.algorithm.study.others.bloom.kylin;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import com.google.common.collect.Maps;

class SimpleHashTest {

    @Test
    void hashInter() {
        SimpleHash simpleHash = new SimpleHash(1000000, 4);

        int max = 0;
        int min = 0;
        Map<Integer, List<String>> hashStat = Maps.newHashMap();
        for (int i = 0; i < simpleHash.getCap(); i++) {
            int h = simpleHash.hash(i);
            max = Math.max(max, h);
            min = Math.min(min, h);
            int j = i;
            hashStat.compute(h, (k, v) -> {
                if (v == null) {
                    v = Lists.newArrayList();
                }
                v.add(j+"");
                return v;
            });
        }
        System.out.println("min=" + min);
        System.out.println("max=" + max);
        System.out.println("hashStat=" + hashStat.size());
    }
    @Test
    void hashStr() {
        SimpleHash simpleHash = new SimpleHash(1000000, 4);

        int max = 0;
        int min = 0;
        Map<Integer, List<String>> hashStat = Maps.newHashMap();
        for (int i = 0; i < simpleHash.getCap(); i++) {
            String uuid= UUID.randomUUID().toString();
            int h = simpleHash.hash(uuid);
            max = Math.max(max, h);
            min = Math.min(min, h);
            int j = i;
            hashStat.compute(h, (k, v) -> {
                if (v == null) {
                    v = Lists.newArrayList();
                }
                v.add(j+"");
                return v;
            });
        }
        System.out.println("min=" + min);
        System.out.println("max=" + max);
        System.out.println("hashStat=" + hashStat.size());

    }
}