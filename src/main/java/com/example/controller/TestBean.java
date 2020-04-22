package com.example.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
// singleton、prototype（如果只在此处加多例，不再controller层加多例，那么注入本类时依然是单例）
//（可能controller单例实例化之后，不会再更改属性的值了。）
// 在此处加多例、controller也加多例，那么，两者都是多例了。
//@Scope("prototype") 
/*
 *首先value就分为四类：

ConfigurableBeanFactory.SCOPE_PROTOTYPE，即“prototype”
ConfigurableBeanFactory.SCOPE_SINGLETON，即“singleton”
WebApplicationContext.SCOPE_REQUEST，即“request”
WebApplicationContext.SCOPE_SESSION，即“session”
  他们的含义是：

singleton和prototype分别代表单例和多例；
request表示请求，即在一次http请求中，被注解的Bean都是同一个Bean，不同的请求是不同的Bean；
session表示会话，即在同一个会话中，被注解的Bean都是使用的同一个Bean，不同的会话使用不同的Bean。
  使用session和request产生了一个新问题，生成controller的时候需要service作为controller的成员，
但是service只在收到请求（可能是request也可能是session）时才会被实例化，controller拿不到service实例。
为了解决这个问题，@Scope注解添加了一个proxyMode的属性，
有两个值ScopedProxyMode.INTERFACES和ScopedProxyMode.TARGET_CLASS，前一个表示表示Service是一个接口，
后一个表示Service是一个类。
  本文遇到的问题中，将@Scope注解改成@Scope(value = WebApplicationContext.SCOPE_REQUEST, 
proxyMode = ScopedProxyMode.TARGET_CLASS)就可以了，这里就不重复贴代码了。

链接：https://www.jianshu.com/p/54b0711a8ec8
 */
// controller上不用加scope注解
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS) // 当前类是普通类，不是接口
public class TestBean {
	private int age;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
