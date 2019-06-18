package com.redis.queue;

import com.redis.queue.reidsqueue.Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedismqApplicationTests {

    @Test
    public void contextLoads() {
    }


    @Autowired
    Producer producer;

    @Test
    public void add(){
        producer.fireEvent("aaaaaaaaaaaaaaa");
    }
}

