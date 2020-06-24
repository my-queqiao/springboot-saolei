package com.example.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.acore.springIntercept.IntercptSessionCheck;
import com.example.controller.LiuyanSaoleiController;

public class TestMethodInfoUtil {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
//		String gen = System.getProperty("user.dir");
//		System.out.println("gen:"+gen);
//		listAllFile(new File(gen+"/src/main/java"));
		
		String fileContent = MethodInfoUtil.readFileContent("C:\\Users\\tom\\Desktop\\User.java");
		List<MethodInfo> methodInfos = MethodInfoUtil.getMethodInfo(fileContent);
		
		String fileContent2 = MethodInfoUtil.readFileContent("C:\\Users\\tom\\Desktop\\User2.java");
		List<MethodInfo> methodInfos2 = MethodInfoUtil.getMethodInfo(fileContent2);
		for (MethodInfo mi : methodInfos) {
			for (MethodInfo mi2 : methodInfos2) {
				if(mi.getMethodName().equals(mi2.getMethodName())&&mi.getParamType().equals(mi2.getParamType()) ) { // 方法名、参数相同
					String methodBody = mi.getMethodBody(); // 主分支
					String methodBody2 = mi2.getMethodBody(); // test分支
					boolean changeOrNot = MethodInfoUtil.changeOrNot(methodBody,methodBody2);
					if(changeOrNot) {
						String methodName = mi2.getMethodName();
						System.out.println(methodName+",该方法改变了");
						String changeAnalyse = MethodInfoUtil.changeAnalyse(methodBody, methodBody2);
						System.out.println("changeAnalyse:"+changeAnalyse);
					}else {
						String methodName = mi2.getMethodName();
						System.out.println(methodName+",该方法未改变");
					}
					continue;
				}
			}
		}
		
		
	}
	
	public static void listAllFile(File f) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		File[] files = f.listFiles();
		for (File file : files) {
			String str = file.toString();
			int end=str.lastIndexOf(".java");
			 if(end==str.length()-5){
				 System.out.println("文件："+file);
//				 if(str.endsWith("MethodInfoUtil.java")) {
//					 continue; // 该类有特殊情况，解析不了。
//				 }
				 String content = MethodInfoUtil.readFileContent(str); // 获取文件内容
				 System.out.println("内容："+content);
				 System.out.println("====================================================");
				 List<MethodInfo> methodInfo = MethodInfoUtil.getMethodInfo(content);
				 for (MethodInfo mi : methodInfo) {
					System.out.println("方法名："+mi.getMethodName());
					System.out.println("参数："+mi.getParamType());
					System.out.println("方法体："+mi.getMethodBody());
					System.out.println("=================================================================");
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
