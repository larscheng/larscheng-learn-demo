package com.lars.www.web;

import com.lars.www.entity.DelayMessageVo;
import com.lars.www.response.Message;
import com.lars.www.service.RedisMqService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 描述:
 * redisMq对外接口
 *
 * @author zhengql
 * @date 2019/1/27 13:45
 */
@Api(tags = "😋RedisMqController", description = "延时任务管理")
@RestController
public class RedisMqController {

    @Autowired
    private RedisMqService redisMqService;


    @GetMapping(value = "/redisMq/addTask")
    @ApiOperation(value = "添加一个延时任务到test队列", notes = "Zset+异步调用方案")
    public Message addTask(@RequestParam(value = "delay")Long delay)  {
        return redisMqService.addTask(delay);
    }


    @PostMapping(value = "/redisMq/delTask")
    @ApiOperation(value = "删除一个延时任务", notes = "Zset+异步调用方案")
    public Message delTask(@RequestBody DelayMessageVo messageVo)  {
        return redisMqService.delTask(messageVo);
    }


    @PostMapping(value = "/redisMq/updateTask")
    @ApiOperation(value = "编辑一个延时任务", notes = "Zset+异步调用方案，list[0]:原DelayMessage，list[1]:修改后的DelayMessage")
    public Message updateTask(@RequestBody List<DelayMessageVo> messageList)  {
        return redisMqService.updateJob(messageList);
    }

    @PostMapping(value = "/redisMq/listTask")
    @ApiOperation(value = "查询延时任务列表", notes = "查询指定队列中的任务列表，支持分页")
    public Message listTask(@RequestBody DelayMessageVo vo)  {
        return redisMqService.listTask(vo);
    }

    @GetMapping(value = "/redisMq/countTask")
    @ApiOperation(value = "各个zSet中延时任务数统计", notes = "")
    public Message countTask()  {
        return redisMqService.countTask();
    }
}
