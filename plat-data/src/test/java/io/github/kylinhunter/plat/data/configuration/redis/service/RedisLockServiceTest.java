package io.github.kylinhunter.plat.data.configuration.redis.service;

import io.github.kylinhunter.plat.data.redis.service.RedisLockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RedisLockServiceTest {

    @Autowired
    private RedisLockService redisLockService;

    @Test
    public void lockTest() {

        redisLockService.lock("lock1", (r) -> System.out.println("do something1"));

        redisLockService.tryLock("lock2", (b) -> {
            System.out.println("whether get lock" + b);
            System.out.println("do something2");
        });
    }
}