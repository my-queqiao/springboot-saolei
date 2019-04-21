package com.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value= {ElementType.METHOD,ElementType.TYPE})//目标，该注解可以加在方法、类上
@Retention(RetentionPolicy.RUNTIME)//保留政策，运行时
public @interface SxtService { //@interface表明该类是要给注解
	String value() default "";//字段为value，类型为String，默认值为空字符串。
}
