package com.redis.queue.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis 工具类
 *
 * @author 应用三组
 * @version 1.0.1
 * @date 2017/11/29
 */
@SuppressWarnings("unchecked")
@Component
public class RedisUtil {
    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);
    @SuppressWarnings("rawtypes")

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private JedisPool jedisPool;

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    public boolean remove(final String key) {
        if (redisTemplate.hasKey(key)) {
            redisTemplate.delete(key);
        }
        return !redisTemplate.hasKey(key);
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            return redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * <p>写入缓存
     * <p>将 key 的值设为 value，当且仅当 key 不存在
     * <p>若给定的 key 已经存在，则 SETNX 不做任何动作
     * <p>SETNX = SET if Not Exists
     *
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public boolean setNX(final String key, Object value, Long expireTime) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        if (!ops.setIfAbsent(key, value)) {
            return false;
        }
        if (expireTime != null) {
            if (!redisTemplate.expire(key, expireTime, TimeUnit.SECONDS)) {
                redisTemplate.delete(key);
                return false;
            }
        }
        return true;
    }

    /**
     * 通过key获取键到期的剩余时间(秒) -1, 如果key没有到期超时。-2, 如果键不存在。
     *
     * @param key
     * @return
     */
    public Long getExpire(final String key) {
        return redisTemplate.getExpire(key);
    }




    public String getValue(final String key, String defaultVal) {
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            String val = (String)operations.get(key);
            return val == null ? defaultVal : val;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultVal;
    }

    /***
     * 写入有序集合
     * @param key
     * @param value
     * @param score
     * @return
     */
    public Boolean zSetAdd(final String key, String value, Long score){
        try {
            ZSetOperations operations = redisTemplate.opsForZSet();
            return operations.add(key, value, score);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /***
     * 删除有序集合
     * @param key
     * @param value
     * @return
     */
    public Boolean zSetDel(final String key, String... value){
        try {
            ZSetOperations operations = redisTemplate.opsForZSet();
            return operations.remove(key, value)>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /****
     * 根据score排序，获取ZSet中指定数量的集合
     * orderByDesc：score自小到大排
     * @param key
     * @param startRange
     * @param endRange
     * @param orderByDesc
     * @return
     */
    public Set<String> getRangeByScore(final String key, int startRange, int endRange, boolean orderByDesc){
        try {
            ZSetOperations<String, String> operations = redisTemplate.opsForZSet();
            if (orderByDesc) {
                return operations.reverseRange(key, startRange, endRange);
            } else {
                return operations.range(key, startRange, endRange);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * 根据key，获取ZSet集合对象的score
     * @param key
     * @param value
     * @return
     */
    public Double getScore(final String key, String value){
        try {
            ZSetOperations operations = redisTemplate.opsForZSet();
            return operations.score(key,value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }





    /**
     * list
     */

    /***
     * 获取list中的数量
     * @param key
     * @return
     */
    public long countList(final String key) {
        if (key == null) {
            return 0L;
        }
        try {
            ListOperations operations = redisTemplate.opsForList();
            return operations.size(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }


    /***
     * 添加list缓存
     * @param key
     * @param val
     * @return
     */
    public Boolean rightPush(final String key,String val) {
        try {
            ListOperations operations = redisTemplate.opsForList();
            return operations.rightPush(key,val)>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /***
     * 获取list中的缓存
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<String> range(final String key, long start, long end) {
        try {
            ListOperations operations = redisTemplate.opsForList();
            return operations.range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * 删除
     * @param key
     * @param count
     * @param value
     * @return
     */
    public Boolean removeListValue(final String key, long count, String value) {
        try {
            ListOperations operations = redisTemplate.opsForList();
            return operations.remove(key,count,value)>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public int removeListValue(String key, long count, List<String> values) {
        int result = 0;
        if (values != null && values.size() > 0) {
            for (String value : values) {
                if (removeListValue(key, count, value)) {
                    result++;
                }
            }
        }
        return result;
    }



    public int removeListValue(String key, List<String> values) {
        return removeListValue(key, 1, values);
    }



    public long lpush(String key, String value){

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            long result = jedis.lpush(key, value);
            return result;
        }catch (Exception e){
            logger.error("Jedis lpush 异常 ：" + e.getMessage());
            return 0;
        }finally {
            if (jedis != null){
                try {
                    jedis.close();
                }catch (Exception e){
                    logger.error(e.getMessage());
                }
            }
        }
    }

    /**
     * 获取指定值
     * @param timeout
     * @param key
     * @return
     */
    public List<String> brpop(int timeout, String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.brpop(timeout, key);
        }catch (Exception e){
            logger.error("Jedis brpop 异常 ：" + e.getMessage());
            return null;
        }finally {
            if (jedis != null){
                try {
                    jedis.close();
                }catch (Exception e){
                    logger.error(e.getMessage());
                }
            }
        }
    }
}
