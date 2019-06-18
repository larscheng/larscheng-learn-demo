package com.redis.queue.reidsqueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2018/12/13 15:11
 */

@RestController
public class TestController {

    @Autowired
    private Producer producer;
    @GetMapping("/test/add")
    public boolean add(@RequestParam("param")String param){
        return producer.fireEvent(param);
    }
}
