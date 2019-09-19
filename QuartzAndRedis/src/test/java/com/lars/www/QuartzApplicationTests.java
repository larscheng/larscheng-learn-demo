package com.lars.www;

import com.lars.www.entity.QuartzEnetity;
import com.lars.www.enums.EnumJobClassType;
import com.lars.www.service.LeaseRegionInfoServiceImpl;
import com.lars.www.service.QuartzJobService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.util.Calendar.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuartzApplicationTests {


    @Autowired
    private QuartzJobService quartzJobService;

    @Test
    public void contextLoads() {
    }


    @Autowired
    private LeaseRegionInfoServiceImpl leaseRegionInfoService;

    /***
     * 压测
     */
    @Test
    public void addNumTest() {
        //像数据库添加数据
        Integer id = leaseRegionInfoService.addDbData();

        //添加job

        for (int i =0;i<100 ;i++){
            leaseRegionInfoService.Job(id,10000000+i);
        }
    }
}
