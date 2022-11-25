package io.github.kylinhunter.study.algorithm.others.bloom.guava;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.google.common.collect.Maps;

@SuppressWarnings({"unchecked", "rawtypes"})
class GuavaKylinBloomFilterAdapterTest {

    @Test
    void contains() {

        GuavaKylinBloomFilterAdapter<Integer> guavaKylinBloomFilterAdapter =
                new GuavaKylinBloomFilterAdapter(1000000, 0.001, Integer.class);

        Map<Integer, Integer> conflicts = Maps.newHashMap();
        for (int i = 0; i < 1000000; i++) {
            if (guavaKylinBloomFilterAdapter.mightContain(i)) {
                conflicts.put(i, 1);
            }
            guavaKylinBloomFilterAdapter.put(i);
        }
        int conflict = 0;

        for (int i = 0; i < 1000000; i++) {

            boolean ok = guavaKylinBloomFilterAdapter.mightContain(i);
            if (ok) {
                conflict++;
            }
        }

        System.out.println("conflicts:" + conflicts.size());
        System.out.println("confict:" + conflict);

    }
}