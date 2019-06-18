package com.redis.queue.reidsqueue;

import com.redis.queue.config.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2018/12/13 13:32
 */
@Service
public class Consumer  implements InitializingBean, ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    private ApplicationContext applicationContext;

    private static final String key = "TEST_QUEUE";
    @Autowired
    RedisUtil redisUtil;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(10);
        for (int i = 0 ; i<3 ;i++){
            threadPoolExecutor.submit(new Worker());
        }
        latch.await();
//        Thread thread = new Thread(new Worker());
//        thread.start();
    }



    class Worker implements Runnable{
        @Override
        public void run() {

            while (true) {
                List<String> events = redisUtil.brpop(0, key);
                for (String str : events) {
                    if (str.equals(key)){
                        continue;
                    }
                    logger.info("接收到" + str);
                }
            }
        }
    }
}