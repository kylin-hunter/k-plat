package io.github.kylinhunter.plat.data.configuration.redis;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

import io.github.kylinhunter.commons.util.ObjectValues;

public class RedisService {
    protected RedisTemplate<String, Serializable> redisTemplate;

    private String DEFAULT_NAME_SPACE = "kplat";

    public RedisService(RedisTemplate<String, Serializable> redisTemplate) {
        super();
        this.redisTemplate = redisTemplate;
        Arrays.stream(RedisKeys.values()).forEach(redisKeys -> {
            redisKeys.setNamespace(DEFAULT_NAME_SPACE);
        });
    }

    /**
     * 设置 String 类型 key-value
     *
     * @param key
     * @param value
     */
    public void set(String key, Long value) {

        redisTemplate.opsForValue().set(key, value);

    }

    /**
     * 设置 String 类型 key-value
     *
     * @param key
     * @param value
     */
    public void set(String key, Serializable value) {

        redisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, Serializable value, long expireSecond) {
        redisTemplate.opsForValue().set(key, value, expireSecond, TimeUnit.SECONDS);
    }

    public void set(String key, Serializable value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    public Long increment(String key, long value) {
        return this.increment(key, new Long(value));
    }

    public Long increment(String key, Long value) {
        return redisTemplate.opsForValue().increment(key, value);
    }

    /**
     * 获取 String 值
     *
     * @param key
     */
    @SuppressWarnings("unchecked")
    public <T extends Serializable> T get(String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    /**
     * 获取 String 值
     *
     * @param key
     */
    @SuppressWarnings("unchecked")
    public Long getLong(String key, Long defaultValue) {
        return ObjectValues.getLong(redisTemplate.opsForValue().get(key), defaultValue);
    }

    /**
     * 获取 String 值
     *
     * @param key
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public void opSetAdd(String key, Serializable value) {
        redisTemplate.opsForSet().add(key, value);
    }

    public <T extends Serializable> T opSetPop(String key) {
        return (T) redisTemplate.opsForSet().pop(key);
    }

    public Serializable opSetSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    public void opZSetAdd(String key, String member, double value) {
        redisTemplate.opsForZSet().add(key, member, value);
    }

    public void opZSetIncrementScore(String key, String member, double value) {
        redisTemplate.opsForZSet().incrementScore(key, member, value);
    }

    public Double opZSetGetScore(String key, String member) {
        return redisTemplate.opsForZSet().score(key, member);
    }

    public <T> T executeLuaScript(RedisScript<T> redisScript, List<String> keys, Object... args) {
        return redisTemplate.execute(redisScript, keys, args);
    }
}