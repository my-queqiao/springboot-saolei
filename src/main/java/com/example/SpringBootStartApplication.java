package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;


/** 打成war包部署时，使用该启动类   （打成war包时，只需要修改这个启动类即可，其余的任何东西不需要改）
 * 修改启动类，继承 SpringBootServletInitializer 并重写 configure 方法
 * 我们需要类似于web.xml的配置方式来启动spring上下文了，在Application类的同级添加一个SpringBootStartApplication类，其代码如下:
 */
//@SpringBootApplication
//@EnableScheduling
//@EnableCaching
//@MapperScan("com.example.mappers") //扫描mybatis映射文件
//public class SpringBootStartApplication extends SpringBootServletInitializer{
//
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(SpringBootStartApplication.class);
//	}
//    public static void main(String[] args) {
//        SpringApplication.run(SpringBootStartApplication.class, args);
//    }
//}
