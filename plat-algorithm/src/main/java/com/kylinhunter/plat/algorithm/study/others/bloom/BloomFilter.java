package com.kylinhunter.plat.algorithm.study.others.bloom;

import java.util.BitSet;

/**
 * Bloom Filter Calculator
 * https://krisives.github.io/bloom-calculator/
 *
 * @author BiJi'an
 * @description
 * @date 2022-07-28 22:42
 **/
public class BloomFilter {

    private BloomType bloomType = BloomType.N_10000000_001;
    private BloomHashGroup bloomHashGroup = new SimpleHashGroup();
    private BitSet bitSet;
    private BloomHash[] bloomHashes;

    public BloomFilter() {
        this(null, null);
    }

    public BloomFilter(BloomType bloomType) {
        this(bloomType, null);
    }

    public BloomFilter(BloomHashGroup bloomHashGroup) {
        this(null, bloomHashGroup);
    }

    public BloomFilter(BloomType bloomType, BloomHashGroup bloomHashGroup) {
        if (bloomType != null) {
            this.bloomType = bloomType;
        }
        if (bloomHashGroup != null) {
            this.bloomHashGroup = bloomHashGroup;
        }
        this.bloomHashes = this.bloomHashGroup.generate(this.bloomType.getM(), this.bloomType.getK());
        this.bitSet = new BitSet(this.bloomType.getM());
    }

    public void add(Object obj) {
        for (BloomHash bloomHash : bloomHashes) {
            bitSet.set(bloomHash.hash(obj), true);
        }

    }

    public boolean contains(Object obj) {
        boolean ret = true;
        for (BloomHash bloomHash : bloomHashes) {
            ret = ret && bitSet.get(bloomHash.hash(obj));
        }
        return ret;
    }

}
