package com.lars.www.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 描述:
 * 读取端口
 *
 * @author zhengql
 * @date 2019/7/26 17:34
 */
@Configuration
public class MyPort {
    @Value("${server.port}")
    private int port;


    public int getPort() {
        return port;
    }

    public MyPort setPort(int port) {
        this.port = port;
        return this;
    }
}
