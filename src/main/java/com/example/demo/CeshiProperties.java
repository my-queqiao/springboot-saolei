package com.example.demo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.stereotype.Component;
//@Component
public class CeshiProperties {
	public CeshiProperties() {
		System.out.println("构造方法");
	}
	static {
		System.out.println("======================执行了吗？=========================");
		new Timer("testTimer").schedule(new TimerTask() {
            @Override
            public void run() {
            	CeshiProperties c = new CeshiProperties();
        		try {
					c.t();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        }, 10000,10000);
	}
	public static void main(String[] args) throws IOException {
		CeshiProperties c = new CeshiProperties();
		c.t();
	}
	public void t() throws IOException {
		String gen = System.getProperty("user.dir"); // 用户的当前工作目录
		System.out.println("用户当前工作目录："+gen);
		  String osName = System.getProperty("os.name"); // 操作系统名称
		  System.out.println("操作系统名称："+osName);
		  String userHome = System.getProperty("user.home"); // 用户的主目录
		  System.out.println("用户的主目录："+userHome);
		  System.out.println("================================================");
		  
		  String path = CeshiProperties.class.getResource("").getPath(); // 类的绝对路径
		  System.out.println("该类的绝对路径:"+path);
		  /**截取WEB-INF目录	*/
		  int index = path.indexOf("WEB-INF");
		  String webInf = path.substring(0, index+"WEB-INF".length());
		  System.out.println("截取到的项目的WEB-INF路径："+webInf);
		  
		  // 读取文件
		  String url = webInf+"/conf/comm.properties";
		  System.out.println("配置文件的路径："+url);
		  BufferedReader in = new BufferedReader(new InputStreamReader(
				  new FileInputStream(url)));
		  String line = null;
		  while((line=in.readLine())!=null) {
			  System.out.println(line);
		  }
	}
}
