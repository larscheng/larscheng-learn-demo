package com.redis.queue.reidsqueue;

import com.alibaba.fastjson.JSONObject;
import com.redis.queue.config.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2018/12/13 13:29
 */

@Service
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    @Autowired
    RedisUtil redisUtil;

    public boolean fireEvent(String param) {
        try {
            for (int i = 0 ; i<10;i++){
                Map<String, Object> map = new HashMap<>();
                map.put("param", param+i);
                //序列化
                String json = JSONObject.toJSONString(map);
                //产生key
                String key = "TEST_QUEUE";
                //放入工作队列中
                redisUtil.lpush(key, json);
            }
            System.out.println("...add success");
            return true;
        } catch (Exception e) {
            logger.error("EventProducer fireEvent 异常 ：" + e.getMessage());
            return false;
        }

    }
}

