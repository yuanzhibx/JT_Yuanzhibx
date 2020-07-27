package com.jt.conf;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jt.quartz.OrderQuartz;

/**
 * @author Yuanzhibx
 * @Date 2020-07-24
 */
@Configuration
public class OrderQuartzConfig {

    /**
     * 定义任务详情
     * newJob(): 任务类型
     * withIdentity(): 任务名称
     */
    @Bean
    public JobDetail orderjobDetail() {
        //指定job的名称和持久化保存任务
        return JobBuilder
                .newJob(OrderQuartz.class)
                .withIdentity("orderQuartz")
                .storeDurably()
                .build();
    }

    /**
     * 定义触发器
     * forJob(): 执行什么样的内容
     * withIdentity(): 任务名称
     * withSchedule: 什么时候执行
     */
    @Bean
    public Trigger orderTrigger() {
		/*SimpleScheduleBuilder builder = SimpleScheduleBuilder.simpleSchedule()
				.withIntervalInMinutes(1)	//定义时间周期
				.repeatForever();*/
		// 设定程序多久执行一次	(1 分钟)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0/1 * * * ?");
        return TriggerBuilder
                .newTrigger()
                .forJob(orderjobDetail())
                .withIdentity("orderQuartz")
                .withSchedule(scheduleBuilder).build();
    }

}
