package com.panzh.teamindexweb.config.scheduled;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author: TMingYi
 * @date: 2020/5/1 22:53
 * 启动静态的定时任务
 */
@Configuration
@EnableScheduling  //启动定时任务、简单的静态间隔代码；
public class SaticScheduleTask {

    //3.添加定时任务
//    @Scheduled(cron = "0/5 * * * * ?")
    //或直接指定时间间隔，例如：5秒
    @Scheduled(fixedRate=1000 * 5)
    private void configureTasks() {
//        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
    }
}
