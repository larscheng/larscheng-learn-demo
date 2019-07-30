package com.lars.www.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lars.www.entity.JobAndTrigger;
import com.lars.www.mapper.JobAndTriggerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class JobAndTriggerImpl  extends ServiceImpl<JobAndTriggerMapper, JobAndTrigger> {

    @Autowired
    private JobAndTriggerMapper jobAndTriggerMapper;



}