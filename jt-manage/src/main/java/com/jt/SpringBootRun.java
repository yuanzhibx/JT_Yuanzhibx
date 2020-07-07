package com.jt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Yuanzhibx
 * @Date 2020-07-06
 */
@SpringBootApplication
@MapperScan("com.jt.mapper")
public class SpringBootRun {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootRun.class, args);
	}
}
