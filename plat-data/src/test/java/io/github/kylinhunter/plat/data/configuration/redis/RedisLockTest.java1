package io.github.kylinhunter.plat.data.configuration.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RedisLockTest {

    @Autowired
    private RedisLock redisLock;

    @Test
    public void lockTest() {

        redisLock.lock("lock1", (r) -> System.out.println("do something1"));

        redisLock.tryLock("lock2", (b) -> {
            System.out.println("whether get lock" + b);
            System.out.println("do something2");
        });
    }
}