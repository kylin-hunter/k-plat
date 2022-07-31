package com.kylinhunter.plat.algorithm.study.others.bloom;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.kylinhunter.plat.algorithm.study.others.bloom.guava.GuavaKylinBloomFilterAdapter;
import com.kylinhunter.plat.algorithm.study.others.bloom.kylin.BloomType;
import com.kylinhunter.plat.algorithm.study.others.bloom.kylin.KylinBloomFilterImp;

/**
 * @author BiJi'an
 * @description
 * @date 2022-07-31 13:45
 **/
@SuppressWarnings("rawtypes")
public class BloomFilterEffectComparison {

    @Test
    public void testString() {
        KylinBloomFilter<String> kylinBloomFilter1 = new KylinBloomFilterImp<>(BloomType.N_1000000_001);
        testString(kylinBloomFilter1, 1000000);

        KylinBloomFilter<String> kylinBloomFilter2 = new GuavaKylinBloomFilterAdapter<>(1000000, 0.001, String.class);
        testString(kylinBloomFilter2, 1000000);
    }

    @Test
    public void testInteger() {
        KylinBloomFilter<Integer> kylinBloomFilter1 = new KylinBloomFilterImp<>(BloomType.N_1000000_001);
        testInteger(kylinBloomFilter1, 1000000);

        KylinBloomFilter<Integer> kylinBloomFilter2 = new GuavaKylinBloomFilterAdapter<>(1000000, 0.001, Integer.class);
        testInteger(kylinBloomFilter2, 1000000);
    }

    @SuppressWarnings("unchecked")
    public void testString(KylinBloomFilter kylinBloomFilter, int cap) {

        System.out.println("================================================================================");
        System.out.println("testFpp==>" + kylinBloomFilter.getClass().getName());
        int mightContainNumber0 = 0;

        List<String> list = new ArrayList<>(cap);
        for (int i = 0; i < cap; i++) {
            String uuid = UUID.randomUUID().toString();

            if (kylinBloomFilter.mightContain(uuid)) {
                mightContainNumber0++;
            }

            kylinBloomFilter.put(uuid);
            list.add(uuid);
        }
        int mightContainNumber1 = 0;
        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        percentFormat.setMaximumFractionDigits(2);

        for (String key : list) {
            if (kylinBloomFilter.mightContain(key)) {
                mightContainNumber1++;
            }
        }

        int mightContainNumber2 = 0;
        for (int i = 0; i < cap; i++) {
            String key = UUID.randomUUID().toString();
            if (kylinBloomFilter.mightContain(key)) {
                mightContainNumber2++;
            }
        }
        System.out.println("布隆过滤器容量：" + cap);

        System.out.println("布隆创建期间冲突的key数：" + mightContainNumber0);
        System.out.println("布隆过滤器的冲突率：" + percentFormat.format((float) mightContainNumber0 / cap));

        System.out.println("布隆识别正确的key数：" + mightContainNumber1);
        System.out.println("布隆过滤器的识别率为：" + percentFormat.format((float) mightContainNumber1 / cap));

        System.out.println("布隆识别错误的key值数：" + mightContainNumber2);
        System.out.println("布隆过滤器的误判率为：" + percentFormat.format((float) mightContainNumber2 / cap));

        System.out.println("###################################################");

    }

    @SuppressWarnings("unchecked")
    public void testInteger(KylinBloomFilter kylinBloomFilter, int cap) {

        System.out.println("================================================================================");
        System.out.println("testFpp==>" + kylinBloomFilter.getClass().getName());
        int mightContainNumber0 = 0;

        List<Integer> list = new ArrayList<>(cap);
        for (int i = 0; i < cap; i++) {

            if (kylinBloomFilter.mightContain(i)) {
                mightContainNumber0++;
            }

            kylinBloomFilter.put(i);
            list.add(i);
        }
        int mightContainNumber1 = 0;
        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        percentFormat.setMaximumFractionDigits(2);

        for (Integer key : list) {
            if (kylinBloomFilter.mightContain(key)) {
                mightContainNumber1++;
            }
        }

        int mightContainNumber2 = 0;
        for (int i = 0; i < cap; i++) {
            if (kylinBloomFilter.mightContain(i + cap)) {
                mightContainNumber2++;
            }
        }
        System.out.println("布隆过滤器容量：" + cap);

        System.out.println("布隆创建期间冲突的key数：" + mightContainNumber0);
        System.out.println("布隆过滤器的冲突率：" + percentFormat.format((float) mightContainNumber0 / cap));

        System.out.println("布隆识别正确的key数：" + mightContainNumber1);
        System.out.println("布隆过滤器的误判率为：" + percentFormat.format((float) mightContainNumber1 / cap));

        System.out.println("布隆识别错误的key值数：" + mightContainNumber2);
        System.out.println("布隆过滤器的误判率为：" + percentFormat.format((float) mightContainNumber2 / cap));

        System.out.println("###################################################");

    }
}
