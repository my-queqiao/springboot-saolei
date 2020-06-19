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
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.acore.springIntercept.IntercptSessionCheck;
import com.example.controller.LiuyanSaoleiController;
import com.example.pojo.MethodInfo;

public class TestMethodInfoUtil {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		String gen = System.getProperty("user.dir");
		System.out.println("gen:"+gen);
		listAllFile(new File(gen+"/src/main/java"));
		
//		String a = "";
//		System.out.println(a);
//		System.out.println("===========");
//		List<MethodInfo> methodInfo = MethodInfoUtil.getMethodInfo(a);
		
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
