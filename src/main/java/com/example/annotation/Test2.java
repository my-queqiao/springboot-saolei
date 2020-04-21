package com.example.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.example.acore.springIntercept.FindAllClassInPackageUtil;
/**
 * 测试，控制反转、依赖注入
 * @author tom
 *
 */
public class Test2 {
	@SxtAutowired
	private static Test test;
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		Map<Class<?>,Object> instances = new HashMap<>();
		//获取该包下的所有class对象。
		Set<Class<?>> classes = FindAllClassInPackageUtil.getClasses("com.example.annotation");
		//迭代class集合
		Iterator<Class<?>> iterator = classes.iterator();
		while(iterator.hasNext()) {
			Class<?> next = iterator.next();
			//获取该class对象的所有注解
			Annotation[] annotations = next.getAnnotations();
			for(Annotation a:annotations) {
				Class<? extends Annotation> annotationType = a.annotationType();//注解类型就是注解的class对象
				//if如果该类，拥有@SxtAnnotation
				if(annotationType == SxtService.class) {
					//找到该包下，被@SxtService注解的类，将该类实例化存放到map中<className,实例>
					instances.put(next, next.newInstance());
				}
			}
		}
		//依赖注入
		Test2 test2 = new Test2();
		//得到所有声明的字段
		Field[] fields = test2.getClass().getDeclaredFields();
		//遍历字段数组
		for(Field field:fields) {
			//得到该字段的class对象
			Class<?> fieldTypeClass = field.getType();
			//得到该字段的注解
			Annotation[] annotations = field.getDeclaredAnnotations();
			//遍历该字段的，所有注解
			for(Annotation a:annotations) {
				//a为该字段的类型的注解之一。
				//System.out.println(a.annotationType());
				//System.out.println(SxtAutowired.class);
				//if该字段拥有@SxtAutowired注解
				if(a.annotationType() == SxtAutowired.class) {
					//遍历容器？中所有class对象，
					Set<Class<?>> keySet = instances.keySet();
					Iterator<Class<?>> iterator2 = keySet.iterator();
					while(iterator2.hasNext()) {
						Class<?> next = iterator2.next();//
						//容器中class，分别与该字段class对象，比较，if相等
						if(fieldTypeClass == next) {
							//为该字段field赋值。 
							//String typeName = fieldTypeClass.getTypeName();
							//System.out.println("typeName:"+typeName);
							//Object object = instances.get(fieldTypeClass);
							Object newInstance = next.newInstance();//Test在map中的实例化
							field.set(test2, newInstance);//field是Test的实例test2对象，中的字段。
						}
					}
				}
			}
		}
		//到此，已经注入成功了。（然后再修改该实例的字段，在用的框架也是先注入，后修改成自己想要的字段值。）
		Test test = test2.test;
		test.name = "i am naive";
		System.out.println("name:"+test.name);
	}
}
