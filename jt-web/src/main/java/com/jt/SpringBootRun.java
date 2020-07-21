package com.jt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
/**
 * Failed to configure a DataSource: 'url' attribute is not 
 * specified and no embedded datasource could be configured.
   Reason: Failed to determine a suitable driver class
 * @author pc
 * springBoot项目 特点:开箱即用
 * 该springBoot启动时,不需要添加数据源
 */
@SpringBootApplication(exclude=DataSourceAutoConfiguration.class)
public class SpringBootRun {
	
	public static void main(String[] args) {
		
		SpringApplication.run(SpringBootRun.class,args);
	}
}
