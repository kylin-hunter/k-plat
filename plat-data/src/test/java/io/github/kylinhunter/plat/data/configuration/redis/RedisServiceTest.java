package io.github.kylinhunter.plat.data.configuration.redis;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.kylinhunter.plat.data.Start;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SpringBootTest(classes = Start.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RedisServiceTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void test() {

        IntStream.range(1, 11).forEach(e -> {

            String key = e + "_test";
            redisService.set(key, new Long(1));
            System.out.println("set::" + key + "::" + 1 + ",get:" + redisService.get(key));
            Assertions.assertEquals(1, redisService.getLong(key, 0L));

            redisService.set(key, new Long(2), 1000);
            System.out.println("set::" + key + "::" + 2 + ",get:" + redisService.get(key));
            Assertions.assertEquals(2, redisService.getLong(key, 0L));

            redisService.increment(key, new Long(6));
            System.out.println("increment::" + key + "::" + 6 + ",get:" + redisService.get(key));
            Assertions.assertEquals(8, redisService.getLong(key, 0L));

            redisService.set(e * 1000 + "", new TestBean(e, "name" + e), 10);

        });

        IntStream.range(1, 11).forEach(i -> {
            String key = i + "_test";
            Integer value = redisService.get(key);
            System.out.println("get::" + key + "::" + value);
            TestBean testBean = redisService.get(i * 1000 + "");
            System.out.println("get::" + key + "::" + testBean);
        });
        TestBean testBean10 = new TestBean(10, "name" + 10);
        Assertions.assertEquals(testBean10.getName(), ((TestBean) redisService.get(10 * 1000 + "")).getName());

    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class TestBean implements java.io.Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    int id;
    String name;
}