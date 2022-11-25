package io.github.kylinhunter.study.algorithm.others.bloom.kylin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author BiJi'an
 * @description
 * @date 2022-07-28 23:38
 **/
public class SimpleHashGroup implements BloomHashGroup {
    private static final int[] SEEDS = new int[] {3, 5, 7, 11, 13, 17, 19, 23, 29, 31};
    private BloomHash[] bloomHashes;
    private final Object lock = new Object();

    @Override
    public BloomHash[] generate(int m, int k) {
        if (this.bloomHashes != null) {
            return this.bloomHashes;
        } else {
            synchronized(lock) {
                if (this.bloomHashes != null) {
                    return this.bloomHashes;
                }

                int size = Math.min(SEEDS.length, k);
                BloomHash[] bloomHashes = new BloomHash[size];
                for (int i = 0; i < size; i++) {
                    bloomHashes[i] = new SimpleHash(m, SEEDS[i]);
                }
                this.bloomHashes = bloomHashes;
                return this.bloomHashes;
            }
        }
    }

}

/**
 * @author BiJi'an
 * @description
 * @date 2022-07-28 22:57
 **/
@RequiredArgsConstructor
@Getter
@Setter
class SimpleHash implements BloomHash {

    private final int cap;
    private final int seed;

    public int hash(Object value) {
        String str = String.valueOf(value);
        return Math.abs(str.hashCode() % cap);

    }

}
