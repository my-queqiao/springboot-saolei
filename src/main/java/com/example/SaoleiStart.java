package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 * springBoot 项目工程  的启动类。。
 * @author tom
 * 注意：启动类的位置，可以放到controller的上级包、同一个包，但不能放到平级包、下级包
 */
//@EnableScheduling // 定时任务
/*	扫描mybatis映射文件？经过测试，扫描的应该是mapper类，否则其不能放入spring容器
 *	但是xml文件需要和mapper类在同一个包下
 *	如果不在同一个包下，就需要配置文件中指出xml文件的位置。
 *	mybatis.mapper-locations=com/example/mappers/xml/*.xml
 */
//@EnableCaching // 启用ehcache 
@SpringBootApplication
@MapperScan("com.example.mappers") /** 测试开发分支*/
public class SaoleiStart extends SpringBootServletInitializer{// extends SpringBootServletInitializer
	public static void main(String[] args) {
		SpringApplication.run(SaoleiStart.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SaoleiStart.class);
	}
	/**
	 * spring boot访问静态资源，两种方式：
	 * 1、classpath/resources路径下面，建立static文件夹，里面放静态资源
	 * 2、src/main/webapp,这是根目录，在根目录下面放静态资源
	 * 3、注意：访问时，不用加static前缀
	 */
	/*
	 * springboot默认扫描规则是：自动扫描启动器类的同包或者其子包的下的注解。。。
		补充：一般对于springboot整合的mybatis，我们写好的是sql映射文件可以通过在启动器类的头部使用
		@MapperScan(value="com.yunhui.springboot.dao")注解来扫描指定位置下的Mapper.xml文件？
		
		<!-- 下面是ssm框架的组件扫描 ，例如他会将@service注解的类实例化到spring容器中-->
	    <context:component-scan base-package="com.wuyuan.webapps" />
	 */
}
