package com.kylinhunter.plat.data.configuration.redis;

import java.util.function.Consumer;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import com.kylinhunter.plat.commons.exception.inner.InitException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RedisLock {
    private final RedissonClient redissonClient;

    /***
     * @title 加锁
     * @description
     * @author BiJi'an
     * @param lockName
     * @param consumer
     * @updateTime 2021/11/19 12:04 上午
     * @return void
     * @throws
     */
    public void lock(String lockName, Consumer<RLock> consumer) {
        RLock lock = redissonClient.getLock(lockName);
        try {
            lock.lock();
            consumer.accept(lock);
        } catch (Exception e) {
            throw new InitException("lock error", e);
        } finally {
            lock.unlock();
        }
    }

    /***
     * @title tryLock
     * @description
     * @author BiJi'an
     * @param lockName
     * @param consumer
     * @updateTime 2021/11/19 12:53 上午
     * @return boolean
     * @throws
     */
    public boolean tryLock(String lockName, Consumer<Boolean> consumer) {
        RLock lock = redissonClient.getLock(lockName);
        boolean success = false;
        try {
            success = lock.tryLock();
            consumer.accept(success);
            return success;
        } catch (Exception e) {
            throw new InitException("lock error", e);
        } finally {
            if (success){
                lock.unlock();
            }
        }
    }

}