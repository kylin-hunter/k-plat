package io.github.kylinhunter.plat.data.configuration.redis.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.kylinhunter.plat.data.redis.service.RedisService;
import java.util.stream.IntStream;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.kylinhunter.plat.data.Start;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

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
      assertEquals(1, redisService.getLong(key, 0L));

      redisService.set(key, new Long(2), 1000);
      System.out.println("set::" + key + "::" + 2 + ",get:" + redisService.get(key));
      assertEquals(2, redisService.getLong(key, 0L));

      redisService.increment(key, new Long(6));
      System.out.println("increment::" + key + "::" + 6 + ",get:" + redisService.get(key));
      assertEquals(8, redisService.getLong(key, 0L));

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
    assertEquals(testBean10.getName(), ((TestBean) redisService.get(10 * 1000 + "")).getName());

  }


  @Test
  public void testSet() {

    String key = "test_set";
    redisService.delete(key);

    assertEquals(0L, redisService.forSetSize(key));

    redisService.forSetAdd(key, "1");
    redisService.forSetAdd(key, "2");
    assertEquals(2L, redisService.forSetSize(key));

    redisService.forSetPop(key);
    assertEquals(1L, redisService.forSetSize(key));

  }

  @Test
  public void testZSet() {

    String key = "test_z_set";
    redisService.delete(key);

    assertEquals(0L, redisService.forZSetSize(key));

    redisService.forZSetAdd(key, "a", 1);
    redisService.forZSetAdd(key, "a", 1);
    redisService.forZSetAdd(key, "a", 1);
    assertEquals(1L, redisService.forZSetScore(key, "a"));
    redisService.forZSetIncrementScore(key, "a", 1);
    assertEquals(2L, redisService.forZSetScore(key, "a"));
    redisService.forZSetAdd(key, "b", 1);
    assertEquals(2L, redisService.forZSetSize(key));

    redisService.forZSetRemove(key, "a");
    assertEquals(1L, redisService.forZSetSize(key));

  }

  @Test
  public void execute() {

    String script = "local key = KEYS[1]\n" +
        "local count = redis.call('set', key,ARGV[1] )\n" +
        "return count";

    boolean result = redisService.execute(new DefaultRedisScript<>() {


      @Override
      public Class getResultType() {
        return Boolean.class;
      }

      @Override
      public String getScriptAsString() {
        return script;
      }
    }, Lists.newArrayList("test.execute::"), "101");

    assertEquals(true, result);

    assertEquals("101", redisService.get("test.execute::"));

  }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class TestBean implements java.io.Serializable {

  /**
   *
   */

  int id;
  String name;
}