package com.example.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.controller.LiuyanSaoleiController;

public class CengjiDiaoyong {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		String gen = System.getProperty("user.dir");
		System.out.println("gen:"+gen);
		listAllFile(new File(gen+"/src/main/java"));
//		listAllFile(new File(gen));
		Class c = LiuyanSaoleiController.class;
		
	}
	public static void listAllFile(File f) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		File[] files = f.listFiles();
		for (File file : files) {
			//System.out.println(file);
			String str = file.toString();
			
			int end=str.lastIndexOf(".java");
			 if(end==str.length()-5){
//				 System.out.println("结尾是.java 文件内容展示：");
				 String content = readFileContent(str); // 获取文件内容
				 if(null != content && content.contains("@Controller")) {
//					 System.out.println("content:"+content);
					 if(str.contains("LiuyanSaoleiController.java")) {
//						 System.out.println(content);
						 System.out.println(str);
						 String lujing = str.split("com")[1];
						 String a = "com\\"+lujing;
						 String replace = a.replace("\\", ".");
						 String replace2 = replace.replace("..", ".");
						 String substring = replace2.substring(0, replace2.length()-5);
						 System.out.println(substring);
						 Class<?> forName = Class.forName(substring); // 得到类的class对象
						 /**通过类的注解，得到该类的父url*/
						 Annotation[] annotations = forName.getAnnotations(); // 得到类的注解
						 for(Annotation an:annotations) {
							 if(an.annotationType().equals(RequestMapping.class)) {
								 RequestMapping rm = (RequestMapping)an; // 转换成对应的注解类型
								 String fuUrl = rm.value()[0]; // 得到RequestMapping注解的value值
								 System.out.println("fuUrl:"+fuUrl);
							 }
						 }
						 /**得到该类的所有的方法,以及可以得到url映射的方法*/
						 Method[] declaredMethods = forName.getDeclaredMethods();
						 for(Method m : declaredMethods) {
							 System.out.println(m.toString()); // 类路径，方法名，参数类型
							 System.out.println(m.getName()); // 单纯的方法名
							 // 得到所有的url方法
							 Annotation[] declaredAnnotations = m.getDeclaredAnnotations();
							 for(Annotation an : declaredAnnotations) {
								 if(an.annotationType().equals(RequestMapping.class)) {
									 RequestMapping rm = (RequestMapping)an; // 转换成对应的注解类型
									 String fuUrl = rm.value()[0]; // 得到RequestMapping注解的value值
									 System.out.println("fuUrl:"+fuUrl);
								 }
							 }
						 }
						 /**层级调用问题。*/
						 
					 }
				 }
			}
			
			if (file.isDirectory()) {
				listAllFile(file);
			}
		}
	}
	public static String readFileContent(String fileName) {
	    File file = new File(fileName);
	    BufferedReader reader = null;
	    StringBuffer sbf = new StringBuffer();
	    try {
	        reader = new BufferedReader(new FileReader(file));
	        String tempStr;
	        while ((tempStr = reader.readLine()) != null) {
	            sbf.append(tempStr+"\r\n");
	        }
	        reader.close();
	        return sbf.toString();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (reader != null) {
	            try {
	                reader.close();
	            } catch (IOException e1) {
	                e1.printStackTrace();
	            }
	        }
	    }
	    return sbf.toString();
	}
}
