package com.example.annotation;

import java.lang.reflect.Field;

/**
 * 实现目标：程序运行时扫描项目下，指定的包，实例化所有被@SxtAnnotation注解的类，存放到map中
 * 并把实例注入到被@SxtAutowired注解的，同类型对象中。
 * @author tom
 *
 */
@SxtService
public class Test {
	String name = "naive";
	public static void main(String[] args) {
		Test.class.getName();
		System.out.println(Test.class.getSimpleName());
		Field[] fields = Test.class.getDeclaredFields();
		System.out.println(fields.length);
	}
}
