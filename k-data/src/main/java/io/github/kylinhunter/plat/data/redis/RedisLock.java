/*
 * Copyright (C) 2023 The k-commons Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.kylinhunter.plat.data.redis;

import io.github.kylinhunter.commons.exception.embed.InitException;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

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
      if (success) {
        lock.unlock();
      }
    }
  }
}
