package io.github.kylinhunter.plat.core.retry;

import io.github.kylinhunter.commons.exception.common.KRuntimeException;
import io.github.kylinhunter.commons.utils.exception.ToRetryException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

class SpringRetryHelperTest {

  @Test
  void test1() throws Exception {

    Map<Class<? extends Throwable>, Boolean> exceptionMap = new HashMap<>();

    exceptionMap.put(ToRetryException.class, true);

    // 构建重试模板实例
    RetryTemplate retryTemplate = new RetryTemplate();

    // 设置重试回退操作策略，主要设置重试间隔时间
    FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
    backOffPolicy.setBackOffPeriod(1);

    // 设置重试策略，主要设置重试次数
    SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy(2, exceptionMap);

    retryTemplate.setRetryPolicy(retryPolicy);
    retryTemplate.setBackOffPolicy(backOffPolicy);
    SpringRetryHelper.defaultRetryer(retryTemplate);

    RetryDemoTask retryDemoTask = new RetryDemoTask();
    Boolean result = SpringRetryHelper.retry(() -> retryDemoTask.retryTask("success"));
    Assertions.assertTrue(result);

    retryDemoTask.reset();
    Assertions.assertThrowsExactly(KRuntimeException.class, () -> {

      SpringRetryHelper.retry(() -> retryDemoTask.retryTask("exception_ingnore"));

    });

    retryDemoTask.reset();
    Assertions.assertThrowsExactly(ToRetryException.class, () -> {

      SpringRetryHelper.retry(() -> retryDemoTask.retryTask("exception_always"));
    });

    retryDemoTask.reset();
    result = SpringRetryHelper.retry(() -> retryDemoTask.retryTask("failed_1"));
    Assertions.assertTrue(result);

    retryDemoTask.reset();
    Assertions.assertThrowsExactly(ToRetryException.class, () -> {

      SpringRetryHelper.retry(() -> retryDemoTask.retryTask("failed_2"));
    });

    retryDemoTask.reset();
    result = SpringRetryHelper.retry(() -> retryDemoTask.retryTask("failed_2"), c -> false);
    Assertions.assertFalse(result);

  }

  @Test
  void test2() throws Exception {
    Map<Class<? extends Throwable>, Boolean> exceptionMap = new HashMap<>();

    exceptionMap.put(ToRetryException.class, true);

    // 构建重试模板实例
    RetryTemplate retryTemplate = new RetryTemplate();

    // 设置重试回退操作策略，主要设置重试间隔时间
    FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
    backOffPolicy.setBackOffPeriod(1);

    // 设置重试策略，主要设置重试次数
    SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy(3, exceptionMap);

    retryTemplate.setRetryPolicy(retryPolicy);
    retryTemplate.setBackOffPolicy(backOffPolicy);

    SpringRetryHelper.registerRetryer(RetryType.TYPE1, retryTemplate);

    RetryDemoTask retryDemoTask = new RetryDemoTask();
    Boolean result = SpringRetryHelper.retry(RetryType.TYPE1,
        () -> retryDemoTask.retryTask("success"));
    Assertions.assertTrue(result);


    retryDemoTask.reset();
    Assertions.assertThrowsExactly(KRuntimeException.class, () -> {

      SpringRetryHelper.retry(RetryType.TYPE1,
          () -> retryDemoTask.retryTask("exception_ingnore"));
    });

    retryDemoTask.reset();
    Assertions.assertThrowsExactly(ToRetryException.class, () -> {

      SpringRetryHelper.retry(RetryType.TYPE1,
          () -> retryDemoTask.retryTask("exception_always"));
    });


    retryDemoTask.reset();
    result = SpringRetryHelper.retry(RetryType.TYPE1, () -> retryDemoTask.retryTask("failed_1"));
    Assertions.assertTrue(result);

    retryDemoTask.reset();
    result = SpringRetryHelper.retry(RetryType.TYPE1, () -> retryDemoTask.retryTask("failed_2"));
    Assertions.assertTrue(result);


    retryDemoTask.reset();
    Assertions.assertThrowsExactly(ToRetryException.class, () -> {

      SpringRetryHelper.retry(RetryType.TYPE1, () -> retryDemoTask.retryTask("failed_3"));

    });

    retryDemoTask.reset();
    Assertions.assertThrowsExactly(ToRetryException.class, () -> {

      SpringRetryHelper.retry(RetryType.TYPE1, () -> retryDemoTask.retryTask("failed_4"));
    });


    retryDemoTask.reset();
    result = SpringRetryHelper.retry(() -> retryDemoTask.retryTask("failed_4"), c -> false);
    Assertions.assertFalse(result);

  }

}
