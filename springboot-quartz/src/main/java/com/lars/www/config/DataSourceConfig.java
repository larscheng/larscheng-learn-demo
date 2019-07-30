package com.lars.www.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;


/**
 * 描述: quartz独立数据源
 *
 * @author zhengql
 * @date 2019/7/30 11:34
 */
@Configuration
public class DataSourceConfig {


    @Bean
    DataSource quartzDataSource() {
        Properties properties = new Properties();
        PropertiesFactoryBean factory = new PropertiesFactoryBean();
        factory.setLocation(new ClassPathResource("/quartz.properties"));
        try {
            factory.afterPropertiesSet();
            properties = factory.getObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(properties.getProperty("org.quartz.dataSource.qzDS.driver"));
        dataSource.setJdbcUrl(properties.getProperty("org.quartz.dataSource.qzDS.URL"));
        dataSource.setUsername(properties.getProperty("org.quartz.dataSource.qzDS.user"));
        dataSource.setPassword(properties.getProperty("org.quartz.dataSource.qzDS.password"));
        return dataSource;
    }
}
