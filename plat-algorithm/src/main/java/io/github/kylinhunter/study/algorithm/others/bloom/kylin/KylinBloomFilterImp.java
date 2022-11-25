package io.github.kylinhunter.study.algorithm.others.bloom.kylin;

import java.util.BitSet;

import io.github.kylinhunter.study.algorithm.others.bloom.KylinBloomFilter;

/**
 * Bloom Filter Calculator
 * https://krisives.github.io/bloom-calculator/
 *
 * @author BiJi'an
 * @description
 * @date 2022-07-28 22:42
 **/
@SuppressWarnings("unused")
public class KylinBloomFilterImp<T> implements KylinBloomFilter<T> {

    private final BitSet bitSet;
    private final BloomHash[] bloomHashes;

    public KylinBloomFilterImp() {
        this(null, null);
    }

    public KylinBloomFilterImp(BloomType bloomType) {
        this(bloomType, null);
    }

    public KylinBloomFilterImp(BloomHashGroup bloomHashGroup) {
        this(null, bloomHashGroup);
    }

    public KylinBloomFilterImp(BloomType bloomType, BloomHashGroup bloomHashGroup) {
        if (bloomType == null) {
            bloomType = BloomType.N_10000000_001;
        }
        if (bloomHashGroup == null) {
            bloomHashGroup = new SimpleHashGroup();
        }
        this.bloomHashes = bloomHashGroup.generate(bloomType.getM(), bloomType.getK());
        this.bitSet = new BitSet(bloomType.getM());
    }

    public boolean put(T obj) {
        for (BloomHash bloomHash : bloomHashes) {
            bitSet.set(bloomHash.hash(obj), true);
        }

        return true;
    }

    public boolean mightContain(T obj) {
        boolean ret = true;
        for (BloomHash bloomHash : bloomHashes) {
            ret = ret && bitSet.get(bloomHash.hash(obj));
        }
        return ret;
    }

}
