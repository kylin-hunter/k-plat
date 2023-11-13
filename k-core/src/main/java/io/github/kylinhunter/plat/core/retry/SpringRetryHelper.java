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
package io.github.kylinhunter.plat.core.retry;

import io.github.kylinhunter.commons.collections.MapUtils;
import io.github.kylinhunter.commons.utils.exception.ToRetryException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * @author BiJi'an
 * @description
 * @date 2023-11-12 21:45
 */
@Slf4j
public class SpringRetryHelper {

  private static final Map<Enum, RetryTemplate> RETRYERS = MapUtils.newHashMap();
  private static RetryTemplate DEFAULT_RETRYER;
  private static long FIXED_PERIOD_TIME = 1000L;
  private static int MAX_RETRY_TIMES = 2;

  private static Map<Class<? extends Throwable>, Boolean> exceptionMap = new HashMap<>();


  static {
    exceptionMap.put(ToRetryException.class, true);

    // 构建重试模板实例
    RetryTemplate retryTemplate = new RetryTemplate();

    // 设置重试回退操作策略，主要设置重试间隔时间
    FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
    backOffPolicy.setBackOffPeriod(FIXED_PERIOD_TIME);

    // 设置重试策略，主要设置重试次数
    SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy(MAX_RETRY_TIMES, exceptionMap);

    retryTemplate.setRetryPolicy(retryPolicy);
    retryTemplate.setBackOffPolicy(backOffPolicy);
    DEFAULT_RETRYER = retryTemplate;
  }

  /**
   * @param type    type
   * @param retryer retryer
   * @return void
   * @title registerRetryer
   * @description registerRetryer
   * @author BiJi'an
   * @date 2023-11-12 23:45
   */
  public static <E extends Enum<E>> void registerRetryer(Enum<E> type, RetryTemplate retryer) {
    RETRYERS.put(type, retryer);
  }

  /**
   * @param retryer retryer
   * @return void
   * @title defaultRetryer
   * @description defaultRetryer
   * @author BiJi'an
   * @date 2023-11-12 23:46
   */
  public static void defaultRetryer(RetryTemplate retryer) {
    DEFAULT_RETRYER = retryer;
  }

  /**
   * @param callable         callable
   * @param recoveryCallback recoveryCallback
   * @return V
   * @title retry
   * @description retry
   * @author BiJi'an
   * @date 2023-11-12 22:45
   */
  public static <V> V retry(RetryTemplate retryTemplate, Callable<V> callable,
      RecoveryCallback<V> recoveryCallback)
      throws Exception {

    if (recoveryCallback != null) {
      return retryTemplate.execute(
          retryContext -> callable.call(),
          recoveryCallback
      );
    } else {
      return retryTemplate.execute(
          retryContext -> callable.call()
      );
    }


  }

  /**
   * @param retryTemplate retryTemplate
   * @param callable      callable
   * @return V
   * @title retry
   * @description retry
   * @author BiJi'an
   * @date 2023-11-14 00:47
   */
  public static <V> V retry(RetryTemplate retryTemplate, Callable<V> callable) throws Exception {
    return retry(retryTemplate, callable, null);
  }


  /**
   * @param type             type
   * @param callable         callable
   * @param recoveryCallback recoveryCallback
   * @return V
   * @title retry
   * @description retry
   * @author BiJi'an
   * @date 2023-11-12 23:37
   */
  public static <V, E extends Enum<E>> V retry(Enum<E> type, Callable<V> callable,
      RecoveryCallback<V> recoveryCallback)
      throws Exception {
    return retry(RETRYERS.get(type), callable, recoveryCallback);
  }

  /**
   * @param type     type
   * @param callable callable
   * @return V
   * @title retry
   * @description retry
   * @author BiJi'an
   * @date 2023-11-14 00:52
   */
  public static <V, E extends Enum<E>> V retry(Enum<E> type, Callable<V> callable)
      throws Exception {
    return retry(RETRYERS.get(type), callable, null);
  }

  /**
   * @param callable         callable
   * @param recoveryCallback recoveryCallback
   * @return V
   * @title retry
   * @description retry
   * @author BiJi'an
   * @date 2023-11-12 23:37
   */
  public static <V> V retry(Callable<V> callable, RecoveryCallback<V> recoveryCallback)
      throws Exception {
    return retry(DEFAULT_RETRYER, callable, recoveryCallback);
  }

  /**
   * @param callable callable
   * @return V
   * @title retry
   * @description retry
   * @author BiJi'an
   * @date 2023-11-14 00:52
   */
  public static <V> V retry(Callable<V> callable) throws Exception {
    return retry(DEFAULT_RETRYER, callable);
  }
}
