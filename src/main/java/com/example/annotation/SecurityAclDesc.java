package com.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) //在哪个元素上使用
@Retention(RetentionPolicy.RUNTIME) //运行时可见
public @interface SecurityAclDesc {
	String value();//不给默认值，使用时必须赋值
}
